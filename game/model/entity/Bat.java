package model.entity;

import model.Model;
import view.MyCanvas;
import view.graphicEntity.BatView;


public class Bat extends Mob {

	BatView m_bv;


	public Bat (double x, double y) {
		super(x, y, Model.BAT_ID);
		m_bv = new BatView(this);
		m_ev = m_bv;
		MyCanvas.getInstance().createEntityView(m_bv);
	}

}
