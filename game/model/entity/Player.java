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

				// haut
				if (m_angle < Math.PI && m_angle > 0 && autreJ.m_hitbox.getY() > moi.m_hitbox.getY())
					return;

				// bas
				if (m_angle > Math.PI && autreJ.m_hitbox.getY() < moi.m_hitbox.getY())
					return;

				// gauche
				if (m_angle > Math.PI / 2 && m_angle < 3 * Math.PI / 2 && autreJ.m_hitbox.getX() > moi.m_hitbox.getX())
					return;

				// droite
				if ((m_angle < Math.PI / 2 || m_angle > 3 * Math.PI / 2) && autreJ.m_hitbox.getX() < moi.m_hitbox.getX())
					return;

			}

			m_hitbox.move(speedX * elapsed / 1000, speedY * elapsed / 1000);
			if (this.equals(torch.porteur))
				torch.update(this);
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

		if (this.equals(torch.porteur)) {
			torch.porteur = null;
		} else if (distance(torch) <= 2) {
			torch.porteur = this;
		}
	}

	@Override
	public void wizz () {

		if (m_possessionCD == 0) {
			Mob closestEnnemy = (Mob) closest(EntityType.ENEMY);
			Mob closestNeutral = (Mob) closest(EntityType.NEUTRAL);

			Mob closestTarget = closestEnnemy;

			if (closestTarget == null) {
				closestTarget = closestNeutral;
			} else {

				if (closestNeutral != null) {
					closestTarget = (distance(closestEnnemy) < distance(closestNeutral)) ? (closestEnnemy)
							: (closestNeutral);
				}
			}

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
		m_hitbox.setX(m.m_hitbox.getX());
		m_hitbox.setY(m.m_hitbox.getY());

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

			if (!m_p.m_hitbox.contactEntity(m_p.m_hitbox.getX(), m_p.m_hitbox.getY())) {
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
