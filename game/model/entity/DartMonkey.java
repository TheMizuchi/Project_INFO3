package model.entity;

import model.Model;


public class DartMonkey extends Entity {
	//DartMonkeyView m_dmv;

	public DartMonkey (double x, double y) {
		super(x, y, Model.DART_MONKEY_ID);
		//m_dmv = new DartMonkeyView(this);
		//m_ev = m_dmv;
		//MyCanvas.getInstance().createEntityView(m_dmv);
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
