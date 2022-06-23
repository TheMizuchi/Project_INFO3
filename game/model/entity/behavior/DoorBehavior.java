package model.entity.behavior;

import model.entity.Entity;
import view.graphicEntity.DoorView;


public class DoorBehavior extends EntityBehavior {

	DoorView m_dv;


	public DoorBehavior (Entity e, DoorView ev) {
		super(e, ev);
		m_dv = ev;
	}
}
