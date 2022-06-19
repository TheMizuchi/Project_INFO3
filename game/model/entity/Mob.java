package model.entity;

public class Mob extends Entity {

	public Mob (double x, double y, int ID) {
		super(x, y, ID);
	}

	@Override
	public void update (long elapsed) {
		// déplacement
		m_automata.step();

		if (m_vecDir.isApplied()) {
			double speedX = m_vecDir.getX() * ENTITY_MAX_SPEED;
			double speedY = m_vecDir.getY() * ENTITY_MAX_SPEED;
			m_hitbox.move(speedX * elapsed / 1000, speedY * elapsed / 1000);
			m_vecDir.setApply(false);
		}
	}

}
