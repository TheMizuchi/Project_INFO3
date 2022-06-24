package model.entity.behavior;

import model.entity.Entity;
import view.graphicEntity.EntityView;
import view.graphicEntity.J2View;


public class J2IceBehavior extends PlayerIceBehavior {

	J2View m_jv;


	public J2IceBehavior (Entity e, EntityView ev) {
		super(e, ev);
		J2View m_jv = (J2View) ev;
	}

}
