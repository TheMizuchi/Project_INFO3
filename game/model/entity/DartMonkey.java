package model.entity;

import model.Model;


public class DartMonkey extends Entity {
	//DartMonkeyView m_dmv;

	public DartMonkey (Model m, double x, double y) {
		super(m, x, y, EntityProperties.DART_MONKEY);
		//m_dmv = new DartMonkeyView(this);
		//m_ev = m_dmv;
		//MyCanvas.getInstance().createEntityView(m_dmv);
	}
}
