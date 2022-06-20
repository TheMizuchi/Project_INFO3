package model.entity;

import controller.RefAutomata;


public abstract class Player extends Entity {

	final static double POSSESSION_RANGE = 10;
	boolean m_possessing;


	public Player (double x, double y, EntityProperties ep) {
		super(x, y, ep);
	}

	@Override
	public void update (long elapsed) {

		if (!m_possessing) {
			Torch torch = Torch.getInstance();
			// déplacement
			m_automata.step();
			double speedX = super.m_vecDir.getX() * ENTITY_MAX_SPEED;
			double speedY = super.m_vecDir.getY() * ENTITY_MAX_SPEED;
			m_hitbox.move(speedX * elapsed / 1000, speedY * elapsed / 1000);
			if (this.equals(torch.porteur))
				torch.update(this);
		}
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
			m_possessing = true;
			m_tangible = false;
		}
	}

	public Player finPossession (int pv, Vector dir) {
		m_pv = pv;
		m_vecDir = dir;
		m_possessing = false;
		m_automata = new RefAutomata(this);
		m_tangible = true;
		return null;
	}
}
