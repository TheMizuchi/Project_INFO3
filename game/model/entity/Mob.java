package model.entity;

import common.MyTimer;
import common.TimerListener;
import controller.RefAutomata;


public class Mob extends Entity {

	protected static final long POSSESSION_DURATION = 10;

	int m_PVMob;
	EntityProperties m_OriginalEP;
	Player m_p;
	long m_PossessionTime;


	public Mob (double x, double y, EntityProperties ep) {
		super(x, y, ep);
		m_OriginalEP = ep;
		m_PVMob = m_pv;
	}

	@Override
	public void update (long elapsed) {
		// déplacement
		m_automata.step();

		if (m_vecDir.isApplied()) {
			double speedX = m_vecDir.getX() * ENTITY_MAX_SPEED;
			double speedY = m_vecDir.getY() * ENTITY_MAX_SPEED;
			m_hitbox.move(speedX * elapsed / 1000, speedY * elapsed / 1000);

			if (m_entityProperties.getEntityType() == EntityType.ENEMY || m_entityProperties.getEntityType() == EntityType.NEUTRAL) {
				m_vecDir.setApply(false);
			}
		}
	}

	@Override
	public void wizz () {
		redevientMechant();
	}

	public void devientGentil (EntityProperties ep, Vector PlayerDir, Player p) {
		m_PVMob = m_pv;
		m_pv = p.m_pv;
		m_entityProperties = ep;
		m_automata = new RefAutomata(this);
		m_vecDir = PlayerDir;
		m_p = p;
		new PossessionTimer(this);
	}

	public void redevientMechant () {

		if (m_PossessionTime > 500 && m_p != null) {
			m_entityProperties = m_OriginalEP;
			m_automata = new RefAutomata(this);
			m_p = m_p.finPossession(m_pv, m_vecDir);
			m_vecDir = new Vector();
			m_pv = m_PVMob;
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
