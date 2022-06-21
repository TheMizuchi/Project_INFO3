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
		Camera.getInstance().setj1(e);
	}
}
