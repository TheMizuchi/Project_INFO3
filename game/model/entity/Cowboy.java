package model.entity;

import view.MyCanvas;
import view.graphicEntity.CowboyView;


public class Cowboy extends Player {

	CowboyView m_cv;


	public Cowboy (double x, double y) {
		super(x, y, EntityProperties.COWBOY);
		m_cv = new CowboyView(this);
		m_ev = m_cv;
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
}