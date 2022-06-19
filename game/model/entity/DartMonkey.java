package model.entity;

import model.Model;


public class DartMonkey extends Mob {
	//DartMonkeyView m_dmv;

	public DartMonkey (double x, double y) {
		super(x, y, Model.DART_MONKEY_ID);
		//m_dmv = new DartMonkeyView(this);
		//m_ev = m_dmv;
		//MyCanvas.getInstance().createEntityView(m_dmv);
	}

}
