package model.entity;

import view.MyCanvas;
import view.graphicEntity.J2View;


public class J2 extends Player {

	J2View m_jv;


	public J2 (double x, double y) {
		super(x, y, EntityProperties.J2);
		m_jv = new J2View(this);
		m_ev = m_jv;
		MyCanvas.getInstance().createEntityView(m_jv);
	}
	
private static J2 INSTANCE = this;
	
	public static J2 getInstance (double x, double y) {

		if (INSTANCE == null) {
			INSTANCE = new J2(x, y);
		}
		return INSTANCE;
	}

	public static J2 getInstance () {

		if (INSTANCE == null) {
			throw new RuntimeException("J2 isn't instance yet what are you doing bro ?");
		}
		return INSTANCE;
	}

}
