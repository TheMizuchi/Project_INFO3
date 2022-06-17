package model.entity;

import model.Model;


public class Bat extends Entity {
	//BatView m_zv;

	public Bat (double x, double y) {
		super(x, y, Model.BAT_ID);
		//m_bv = new ZombieView(this);
		//m_ev = m_bv;
		//MyCanvas.getInstance().createEntityView(m_bv);
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
