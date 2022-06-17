package model.entity;

import model.Model;
import view.MyCanvas;
import view.graphicEntity.BloonView;


public class Bloon extends Entity {

	BloonView m_bv;


	public Bloon (double x, double y) {
		super(x, y, Model.BLOON_ID);
		m_bv = new BloonView(this);
		m_ev = m_bv;
		MyCanvas.getInstance().createEntityView(m_bv);
	}

	@Override
	public void pop () {
		m_bv.explode();
	}

	@Override
	public void rotation (double dir) {
		//ROTATE
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
