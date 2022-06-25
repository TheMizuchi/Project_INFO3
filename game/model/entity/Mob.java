package model.entity;

import common.MyTimer;
import common.TimerListener;
import controller.RefAutomata;
import model.Camera;
import model.entity.behavior.MobBehavior;


public abstract class Mob extends Entity {

	protected static final long POSSESSION_DURATION = 10;

	int m_PVMob;
	EntityProperties m_OriginalEP;
	Player m_p;
	long m_PossessionTime;
	MobBehavior m_mb;


	public Mob (double x, double y, EntityProperties ep) {
		super(x, y, ep);
		m_OriginalEP = ep;
		m_PVMob = m_pv;
	}

	@Override
	public void update (long elapsed) {
		// déplacement
		m_automata.step();
		
		if (cdDmgTaken != 0)
			cdDmgTaken--;
		if (cdAction != 0)
			cdAction--;

		if (m_vecDir.isApplied()) {
			double speedX = m_vecDir.getX() * MobMaxSpeed;
			double speedY = m_vecDir.getY() * MobMaxSpeed;

			//Dans le cas où le monstre est possédé
			///////////////////////////////////////
			if (m_p != null) {

				if (Camera.getBlock()) {
					Entity autreJ = autreJ();
					Entity moi = getEntity();
					double m_angle = m_vecDir.getAngle();

					// haut
					if (m_angle < Math.PI && m_angle > 0 && autreJ.m_hitbox.getP1().getY() > moi.m_hitbox.getP1().getY())
						return;

					// bas
					if (m_angle > Math.PI && autreJ.m_hitbox.getP1().getY() < moi.m_hitbox.getP1().getY())
						return;

					// gauche
					if (m_angle > Math.PI / 2 && m_angle < 3 * Math.PI / 2 && autreJ.m_hitbox.getP1().getX() > moi.m_hitbox.getP1().getX())
						return;

					// droite
					if ((m_angle < Math.PI / 2 || m_angle > 3 * Math.PI / 2) && autreJ.m_hitbox.getP1().getX() < moi.m_hitbox.getP1().getX())
						return;

				}
			}
			//Fin cas de possession
			m_hitbox.move(speedX * elapsed / 1000, speedY * elapsed / 1000);

			if (m_entityProperties.getEntityType() == EntityType.ENEMY || m_entityProperties.getEntityType() == EntityType.NEUTRAL) {
				m_vecDir.setApply(false);
			}
		}
	}

	@Override
	public void wizz () {
		m_mb.wizz();
	}
	
	@Override
	public void pop () {
		m_eb.pop();
	}

	private Entity autreJ () {

		if (this.m_p == J1.getInstance())
			return J2.getInstance().getEntity();
		return J1.getInstance().getEntity();
	}

	public Entity getEntity () {
		if (m_p != null)
			return m_p;
		return this;
	}

	public void devientGentil (EntityProperties ep, Vector PlayerDir, Player p) {

		// On arrete l'animation de déplacement s'il y en a une
		if (m_vecDir.getX() != 0 || m_vecDir.getY() != 0) {
			m_ev.walk();
		}

		m_PVMob = m_pv;
		m_pv = p.m_pv;
		m_entityProperties = ep;
		m_automata = new RefAutomata(this);
		m_vecDir = PlayerDir;
		m_p = p;

		// Si besoin on lance l'animation de déplacement
		if (m_vecDir.getX() != 0 || m_vecDir.getY() != 0) {
			m_ev.walk();
		}
		new PossessionTimer(this);
	}

	public void redevientMechant () {

		if (m_PossessionTime > 500 && m_p != null) {

			if (m_vecDir.getX() != 0 || m_vecDir.getY() != 0) {
				m_ev.walk();
			}

			m_entityProperties = m_OriginalEP;
			m_automata = new RefAutomata(this);
			m_p = m_p.finPossession(this, m_pv, m_vecDir);
			m_vecDir = new Vector();
			m_pv = m_PVMob;

			if (m_vecDir.getX() != 0 || m_vecDir.getY() != 0) {
				m_ev.walk();
			}
		}
	}


	private class PossessionTimer implements TimerListener {

		Mob m_mob;
		long m_last;


		PossessionTimer (Mob mob) {
			m_mob = mob;
			m_mob.m_PossessionTime = 0;
			MyTimer mt = MyTimer.getTimer();
			m_last = System.currentTimeMillis();
			mt.setTimer(100, this);
		}

		@Override
		public void expired () {
			long time = System.currentTimeMillis();
			m_mob.m_PossessionTime += time - m_last;

			if (m_mob.m_PossessionTime > POSSESSION_DURATION * 1000 && m_mob.m_p != null) {
				redevientMechant();
			} else {
				MyTimer mt = MyTimer.getTimer();
				mt.setTimer(100, this);
				m_last = time;
			}
		}

	}
}
