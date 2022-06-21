package model.entity;

import view.MyCanvas;
import view.graphicEntity.BatView;


public class Bat extends Mob {

	BatView m_bv;


	public Bat (double x, double y) {
		super(x, y, EntityProperties.BAT);
		m_bv = new BatView(this);
		m_ev = m_bv;
		MyCanvas.getInstance().createEntityView(m_bv);
	}

	//Constructeur pour créer entité sans view
	public Bat (double x, double y, Object o) {
		super(x, y, EntityProperties.BAT);
	}

}