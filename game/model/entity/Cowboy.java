package model.entity;

import model.entity.behavior.CowboyBehavior;
import view.MyCanvas;
import view.graphicEntity.CowboyView;


public class Cowboy extends Player {

	CowboyView m_cv;


	public Cowboy (double x, double y) {
		super(x, y, EntityProperties.COWBOY);
		m_cv = new CowboyView(this);
		m_ev = m_cv;
		m_pb = new CowboyBehavior(this, m_cv);
		m_eb = m_pb;
		MyCanvas.getInstance().createEntityView(m_cv);
	}

	//Constructeur pour créer entité sans view
	public Cowboy (double x, double y, Object o) {
		super(x, y, EntityProperties.COWBOY);
		m_pb = new CowboyBehavior(this, m_cv);
		m_eb = m_pb;
		MyCanvas.getInstance().createEntityView(m_cv);
	}

	@Override
	public void onGround () {}

	@Override
	public void onIce () {}

	@Override
	void hide () {}

	@Override
	void show () {}

	@Override
	void setCam (Entity e) {}

}
