package model.entity;

import model.Model;
import view.MyCanvas;
import view.graphicEntity.J1View;


public class J1 extends Entity {

	J1View m_jv;


	public J1 (Model m, double x, double y) {
		super(m, x, y, EntityProperties.J1);
		m_jv = new J1View(this);
		m_ev = m_jv;
		MyCanvas.getInstance().createEntityView(m_jv);
	}
}
