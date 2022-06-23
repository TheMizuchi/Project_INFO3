package model.entity.behavior;

import model.entity.Entity;
import view.graphicEntity.J2View;


public class J2Behavior extends EntityBehavior {

	J2View m_jv;


	public J2Behavior (Entity e, J2View ev) {
		super(e, ev);
		m_jv = ev;
	}

}
