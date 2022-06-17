package model.entity;

import model.Model;
import view.MyCanvas;
import view.graphicEntity.BatView;


public class Bat extends Entity {

	BatView m_bv;


	public Bat (Model m, double x, double y) {
		super(m, x, y, EntityProperties.BAT);
		m_bv = new BatView(this);
		m_ev = m_bv;
		MyCanvas.getInstance().createEntityView(m_bv);
	}

}
