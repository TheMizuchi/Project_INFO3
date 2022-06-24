package model.entity.behavior;

import model.entity.Entity;
import model.entity.Vector;
import view.graphicEntity.EntityView;
import view.graphicEntity.J1View;


public class J1IceBehavior extends PlayerIceBehavior {

	J1View m_jv;


	public J1IceBehavior (Entity e, EntityView ev) {
		super(e, ev);
		m_jv = (J1View) ev;
	}

	@Override
	public void hit (Vector v) {
		super.hit(v);
		m_jv.attack();
	}

}
