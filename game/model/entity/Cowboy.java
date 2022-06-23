package model.entity;

import model.entity.behavior.CowboyBehavior;
import view.MyCanvas;
import view.graphicEntity.CowboyView;


public class Cowboy extends Player {

	CowboyView m_cv;
	CowboyBehavior m_cb;


	public Cowboy (double x, double y) {
		super(x, y, EntityProperties.COWBOY);
		m_cv = new CowboyView(this);
		m_ev = m_cv;
		m_cb = new CowboyBehavior(this, m_cv);
		this.eb = m_cb;
		MyCanvas.getInstance().createEntityView(m_cv);
	}

	//Constructeur pour créer entité sans view
	public Cowboy (double x, double y, Object o) {
		super(x, y, EntityProperties.COWBOY);
	}

	@Override
	public void pop () {
		m_cv.spin();
	}

	@Override
	void hide () {}

	@Override
	void show () {}

	@Override
	void setCam (Entity e) {}
}
