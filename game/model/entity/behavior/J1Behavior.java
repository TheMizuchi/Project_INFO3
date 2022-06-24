package model.entity.behavior;

import model.entity.Entity;
import model.entity.Vector;
import view.graphicEntity.J1View;


public class J1Behavior extends PlayerBehavior {

	J1View m_jv;


	public J1Behavior (Entity e, J1View ev) {
		super(e, ev);
		m_jv = ev;
	}

	@Override
	public void hit (Vector v) {
		super.hit(v);
		m_jv.attack();
	}

}
