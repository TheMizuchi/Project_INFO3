package model.entity;

import model.entity.behavior.ArrowBehavior;
import view.MyCanvas;
import view.graphicEntity.ArrowView;


public class Arrow extends Mob {

	double m_speed;
	ArrowView m_av;
	ArrowBehavior m_ab;


	public Arrow (double x, double y) {
		super(x, y, EntityProperties.ARROW);
		m_speed = Entity.MobMaxSpeed;
		m_av = new ArrowView(this);
		m_ev = m_av;
		m_ab = new ArrowBehavior(this, m_av);
		m_eb = m_ab;
		MyCanvas.getInstance().createEntityView(m_av);
		m_blockInterdit.removeAt(0);
		m_tangible = false;
		m_hurtbox.setTo(m_hitbox);
	}

	public void setVector (Vector v) {
		m_vecDir = v;
	}

	@Override
	void takeDamages (int damages) {
		return;
	}

}
