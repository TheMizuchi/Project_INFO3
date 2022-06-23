package model.entity;

import common.MyTimer;
import common.TimerListener;
import controller.RefAutomata;
import model.Camera;


public abstract class Player extends Entity {

	protected static final long POSSESSION_CD = 30;

	final static double SLOW_TORCHE = 0.20;
	final static double POSSESSION_RANGE = 10;
	long m_possessionCD;
	Mob m_possessing;


	public Player (double x, double y, EntityProperties ep) {
		super(x, y, ep);
	}

	@Override
	public void update (long elapsed) {

		if (m_possessing == null) {
			Torch torch = Torch.getInstance();
			Key key = Key.getInstance();
			// déplacement
			m_automata.step();

			double speedX;
			double speedY;

			if (this.equals(torch.porteur)) {
				speedX = super.m_vecDir.getX() * ENTITY_MAX_SPEED * (1 - SLOW_TORCHE);
				speedY = super.m_vecDir.getY() * ENTITY_MAX_SPEED * (1 - SLOW_TORCHE);
			} else {
				speedX = super.m_vecDir.getX() * ENTITY_MAX_SPEED;
				speedY = super.m_vecDir.getY() * ENTITY_MAX_SPEED;
			}

			if (Camera.getBlock()) {
				Entity autreJ = autreJ();
				Entity moi = getEntity();
				double m_angle = m_vecDir.getAngle();

				double distY = Math.abs(autreJ.m_hitbox.getP1().getY() - (moi.m_hitbox.getP1().getY() + (speedY * elapsed / 1000))); // distance future entre les 2 joueurs
				double distX = Math.abs(autreJ.m_hitbox.getP1().getX() - (moi.m_hitbox.getP1().getX() + (speedX * elapsed / 1000)));

				// haut
				if (m_angle < Math.PI && m_angle > 0 && distY > Camera.DISTANCE_MAX_Y) // si la distance sur cet axe est supérieur au max
					return;

				// bas
				if (m_angle > Math.PI && distY > Camera.DISTANCE_MAX_Y)
					return;

				// gauche
				if (m_angle > Math.PI / 2 && m_angle < 3 * Math.PI / 2 && distX > Camera.DISTANCE_MAX_X)
					return;

				// droite
				if ((m_angle < Math.PI / 2 || m_angle > 3 * Math.PI / 2) && distX > Camera.DISTANCE_MAX_X)
					return;

			}

			m_hitbox.move(speedX * elapsed / 1000, speedY * elapsed / 1000);
			if (this.equals(torch.porteur))
				torch.update(this);
			if (this.equals(key.porteur))
				key.update(this);

		}
	}

	private Entity autreJ () {

		if (this == J1.getInstance())
			return J2.getInstance().getEntity();
		return J1.getInstance().getEntity();
	}

	public Entity getEntity () {
		if (m_possessing != null)
			return m_possessing;
		return this;
	}

	@Override
	public void pick () {
		Torch torch = Torch.getInstance();
		Key key = Key.getInstance();

		if (distance(key) <= 2 && key.porteur == null) {
			key.porteur = this;
			key.hide();
		}

		else if (this.equals(torch.porteur)) {
			torch.porteur = null;
		} else if (distance(torch) <= 2) {
			torch.porteur = this;
		}
	}

	@Override
	public void wizz () {

		if (m_possessionCD == 0) {

			Mob closestTarget = (Mob) closest(true);

			if (closestTarget != null && distance(closestTarget) < POSSESSION_RANGE) {
				closestTarget.devientGentil(m_entityProperties, m_vecDir.clone(), this);
				m_automata = new RefAutomata(this, true);
				m_possessing = closestTarget;
				m_tangible = false;
				hide();
				setCam(closestTarget);

				// On arrete l'animation de déplacement s'il y en a une
				if (m_vecDir.getX() != 0 || m_vecDir.getY() != 0) {
					m_ev.walk();
				}
			}
		}
	}

	abstract void hide ();
	abstract void show ();
	abstract void setCam (Entity e);

	public Player finPossession (Mob m, int pv, Vector dir) {
		m_pv = pv;
		m_vecDir = dir;
		m_possessing = null;
		m_automata = new RefAutomata(this);
		show();
		new PossessionTimerCD(this);
		setCam(this);
		new IntangibleTimer(this);
		Hitbox hit = new Hitbox(m.m_hitbox.getP1(), m.m_hitbox.getP2(), m.m_hitbox.getP3(), m.m_hitbox.getP4(), m);
		m_hitbox = hit;

		// Si besoin on lance l'animation de déplacement
		if (m_vecDir.getX() != 0 || m_vecDir.getY() != 0) {
			m_ev.walk();
		}
		return null;
	}


	private class IntangibleTimer implements TimerListener {

		Player m_p;


		IntangibleTimer (Player p) {
			m_p = p;
			m_p.m_possessionCD = POSSESSION_CD * 1000;
			m_p.m_tangible = false;
			MyTimer mt = MyTimer.getTimer();
			mt.setTimer(20, this);
		}

		@Override
		public void expired () {

			if (!m_p.m_hitbox.contactEntity(m_p.m_hitbox.getP1(), m_p.m_hitbox.getP2(), m_p.m_hitbox.getP3(), m_p.m_hitbox.getP4())) {
				m_p.m_tangible = true;
			} else {
				MyTimer mt = MyTimer.getTimer();
				mt.setTimer(20, this);
			}

		}

	}

	private class PossessionTimerCD implements TimerListener {

		Player m_p;
		long m_last;


		PossessionTimerCD (Player p) {
			m_p = p;
			m_p.m_possessionCD = POSSESSION_CD * 1000;
			MyTimer mt = MyTimer.getTimer();
			m_last = System.currentTimeMillis();
			mt.setTimer(100, this);
		}

		@Override
		public void expired () {
			long time = System.currentTimeMillis();
			m_p.m_possessionCD -= time - m_last;

			if (m_p.m_possessionCD <= 0) {
				m_p.m_possessionCD = 0;
			} else {
				MyTimer mt = MyTimer.getTimer();
				mt.setTimer(100, this);
				m_last = time;
			}
		}

	}
}
