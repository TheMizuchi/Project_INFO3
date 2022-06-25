package model.entity;

import model.entity.behavior.ArrowBehavior;
import view.MyCanvas;
import view.graphicEntity.ArrowView;


public class Arrow extends Entity {

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
	}

	public void setVector (Vector v) {
		m_vecDir = v;
	}

}
