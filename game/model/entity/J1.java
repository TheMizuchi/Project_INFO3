package model.entity;

import model.Camera;
import model.entity.behavior.J1IceBehavior;
import model.entity.behavior.J1NormalGroundBehavior;
import view.MyCanvas;
import view.graphicEntity.J1View;


public class J1 extends Player {

	J1View m_jv;


	public J1 (double x, double y) {
		super(x, y, EntityProperties.J1);
		m_jv = new J1View(this);
		m_ev = m_jv;
		MyCanvas.getInstance().createEntityView(m_jv);
		onGround();
	}

	@Override
	public void onGround () {
		m_pb = new J1NormalGroundBehavior(this, m_ev);
		m_eb = m_pb;
	}

	@Override
	public void onIce () {
		m_pb = new J1IceBehavior(this, m_ev);
		m_eb = m_pb;
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
	public void hide () {
		m_jv.hide();
	}

	@Override
	public void show () {
		m_jv.show();
	}

	@Override
	public void setCam (Entity e) {
		Camera.getInstance().setj1(e);
	}

}
