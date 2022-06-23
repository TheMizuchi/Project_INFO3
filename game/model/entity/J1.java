package model.entity;

import model.Camera;
import model.entity.behavior.J1Behavior;
import view.MyCanvas;
import view.graphicEntity.J1View;


public class J1 extends Player {

	J1View m_jv;
	J1Behavior m_jb;


	public J1 (double x, double y) {
		super(x, y, EntityProperties.J1);
		m_jv = new J1View(this);
		m_ev = m_jv;
		m_jb = new J1Behavior(this, m_jv);
		m_eb = m_jb;
		MyCanvas.getInstance().createEntityView(m_jv);
	}


	private static J1 INSTANCE = null;


	public static J1 getInstance (double x, double y) {

		if (INSTANCE == null) {
			INSTANCE = new J1(x, y);
		}
		return INSTANCE;
	}

	public static J1 getInstance () {

		if (INSTANCE == null) {
			throw new RuntimeException("J1 isn't instanced yet what are you doing bro ?");
		}
		return INSTANCE;
	}

	public J1 (double x, double y, Object object) {
		super(x, y, EntityProperties.J1);
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

	@Override
	public void hit (Vector v) {
		super.hit(v);
		m_jv.attack();
	}

}
