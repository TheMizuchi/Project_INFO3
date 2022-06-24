package model.entity.behavior;

import model.entity.Entity;
import model.entity.Vector;
import view.graphicEntity.EntityView;
import view.graphicEntity.J2View;


public class J2NormalGroundBehavior extends PlayerNormalGroundBehavior {

	J2View m_jv;


	public J2NormalGroundBehavior (Entity e, EntityView ev) {
		super(e, ev);
		m_jv = (J2View) ev;
	}

	@Override
	public void hit (Vector v) {
		super.hit(v);
		m_jv.attack();
	}

}
