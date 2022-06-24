package model.entity;

import common.MyTimer;
import common.TimerListener;
import controller.RefAutomata;
import model.Camera;
import model.entity.behavior.PlayerBehavior;


public abstract class Player extends Entity {

	public static final long POSSESSION_CD = 30;
	public final static double SLOW_TORCHE = 0.20;
	public final static double POSSESSION_RANGE = 10;
	long m_possessionCD;
	Mob m_possessing;
	PlayerBehavior m_pb;

	double m_speedX, m_speedY;


	public Player (double x, double y, EntityProperties ep) {
		super(x, y, ep);
	}

	abstract public void onGround ();

	abstract public void onIce ();

	@Override
	public void update (long elapsed) {
		(m_pb).update(elapsed);
	}

	public void updateOnNormalGround (long elapsed) {

		if (m_possessing == null) {
			Torch torch = Torch.getInstance();
			Key key = Key.getInstance();
			// déplacement
			m_automata.step();

			if (cdAction != 0)
				cdAction--;
			else {
				double speedX = super.m_vecDir.getX() * EntityMaxSpeed;
				double speedY = super.m_vecDir.getY() * EntityMaxSpeed;

				if (this.equals(torch.porteur)) {
					speedX *= (1 - SLOW_TORCHE);
					speedY *= (1 - SLOW_TORCHE);
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
				m_speedX = speedX * elapsed / 1000;
				m_speedY = speedY * elapsed / 1000;
				m_hitbox.move(m_speedX, m_speedY);
				if (this.equals(torch.porteur))
					torch.update(this);
				if (this.equals(key.porteur))
					key.update(this);
			}
		}

	}

	public void updateOnIce (long elapsed) {

		if (m_possessing == null) {
			Torch torch = Torch.getInstance();
			Key key = Key.getInstance();
			// déplacement
			m_automata.step();

			double speedX;
			double speedY;

			speedX = m_speedX + 0.5 * ENTITY_MAX_ACCELERATION * elapsed / 1000 * elapsed / 1000 * m_vecDir.getX();
			speedY = m_speedY + 0.5 * ENTITY_MAX_ACCELERATION * elapsed / 1000 * elapsed / 1000 * m_vecDir.getY();

			if (this.equals(torch.porteur)) {
				speedX *= (1 - SLOW_TORCHE);
				speedY *= (1 - SLOW_TORCHE);
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
			if (Math.abs(speedX) < 0.5)
				m_speedX = speedX;
			if (Math.abs(speedY) < 0.5)
				m_speedY = speedY;

			m_hitbox.move(m_speedX, m_speedY);
			if (this.equals(torch.porteur))
				torch.update(this);
			if (this.equals(key.porteur))
				key.update(this);
		}

	}

	public void setAutomata (RefAutomata a) {
		m_automata = a;
	}

	public void setPossessedMob (Mob m) {
		m_possessing = m;
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

	public void pickTorch () {
		Torch torch = Torch.getInstance();
		Key key = Key.getInstance();

		if (distance(key) <= 2 && key.porteur == null) {
			key.porteur = this;
			key.hide();
		} else if (this.equals(torch.porteur)) {
			torch.m_ls.setRadius(Torch.GROUND_RADIUS);
			torch.porteur = null;
			torch.show();
		} else if (distance(torch) <= 2) {
			torch.m_ls.setRadius(Torch.HOLDED_RADIUS);
			torch.porteur = this;
			torch.hide();
		}
	}

	@Override
	public void pop () {
		pick();
	}

	@Override
	public void wizz () {
		possession();
	}

	public void possession () {

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
