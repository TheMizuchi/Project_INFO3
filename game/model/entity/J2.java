package model.entity;

import model.Camera;
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


	private static J2 INSTANCE = null;


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

	public J2 (double x, double y, Object object) {
		super(x, y, EntityProperties.J2);
	}

	@Override
	void hide () {
		m_jv.hide();
	}

	@Override
	void show () {
		m_jv.show();
	}

	@Override
	void setCam (Entity e) {
		Camera.getInstance().setj2(e);
	}
}
