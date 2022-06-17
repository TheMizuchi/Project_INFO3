package model.entity;

import view.MyCanvas;
import view.graphicEntity.J2View;


public class J2 extends Entity {

	J2View m_jv;


	public J2 (double x, double y) {
		super(x, y, EntityProperties.J2);
		m_jv = new J2View(this);
		m_ev = m_jv;
		MyCanvas.getInstance().createEntityView(m_jv);
	}
}
