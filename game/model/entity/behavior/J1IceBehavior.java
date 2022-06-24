package model.entity.behavior;

import model.entity.Entity;
import view.graphicEntity.EntityView;
import view.graphicEntity.J1View;


public class J1IceBehavior extends PlayerIceBehavior {

	J1View m_jv;


	public J1IceBehavior (Entity e, EntityView ev) {
		super(e, ev);
		J1View m_jv = (J1View) ev;
	}

}
