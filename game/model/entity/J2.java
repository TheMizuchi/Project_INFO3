package model.entity;

import model.Camera;
import model.entity.behavior.J2IceBehavior;
import model.entity.behavior.J2NormalGroundBehavior;
import view.MyCanvas;
import view.graphicEntity.J2View;


public class J2 extends Player {

	J2View m_jv;


	public J2 (double x, double y) {
		super(x, y, EntityProperties.J2);
		m_jv = new J2View(this);
		m_ev = m_jv;
		MyCanvas.getInstance().createEntityView(m_jv);
		onGround();
	}

	@Override
	public void onGround () {
		m_pb = new J2NormalGroundBehavior(this, m_ev);
		m_eb = m_pb;
	}

	@Override
	public void onIce () {
		m_pb = new J2IceBehavior(this, m_ev);
		m_eb = m_pb;
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
	public void hide () {
		m_jv.hide();
	}

	@Override
	public void show () {
		m_jv.show();
	}

	@Override
	public void setCam (Entity e) {
		Camera.getInstance().setj2(e);
	}

}
