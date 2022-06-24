package model.entity.behavior;

import model.entity.Entity;
import view.graphicEntity.J1View;


public class J1Behavior extends EntityBehavior {

	J1View m_jv;


	public J1Behavior (Entity e, J1View ev) {
		super(e, ev);
		m_jv = ev;
	}

}