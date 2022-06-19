package model.entity;

import model.Model;
import view.MyCanvas;
import view.graphicEntity.J1View;


public class J1 extends Player {

	J1View m_jv;


	public J1 (double x, double y) {
		super(x, y, Model.J1_ID);
		m_jv = new J1View(this);
		m_ev = m_jv;
		MyCanvas.getInstance().createEntityView(m_jv);
	}

}
