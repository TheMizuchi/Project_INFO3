package model.entity;

import view.MyCanvas;
import view.graphicEntity.J1View;


public class J1 extends Player {

	J1View m_jv;


	public J1 (double x, double y) {
		super(x, y, EntityProperties.J1);
		m_jv = new J1View(this);
		m_ev = m_jv;
		MyCanvas.getInstance().createEntityView(m_jv);
	}

	public J1 (double x, double y, Object object) {
		super(x, y, EntityProperties.J1);
	}

}