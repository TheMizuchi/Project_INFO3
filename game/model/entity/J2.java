package model.entity;

import model.Model;
import view.MyCanvas;
import view.graphicEntity.J2View;


public class J2 extends Entity {

	J2View m_jv;


	public J2 (Model m, double x, double y) {
		super(m, x, y, EntityProperties.J2);
		m_jv = new J2View(this);
		m_ev = m_jv;
		MyCanvas.getInstance().createEntityView(m_jv);
	}
}
