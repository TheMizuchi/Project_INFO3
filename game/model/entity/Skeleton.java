package model.entity;

import model.Model;
import view.MyCanvas;
import view.graphicEntity.SkeletonView;


public class Skeleton extends Entity {

	SkeletonView m_sv;


	public Skeleton (double x, double y) {
		super(x, y, Model.SKELETON_ID);
		m_sv = new SkeletonView(this);
		m_ev = m_sv;
		MyCanvas.getInstance().createEntityView(m_sv);
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
