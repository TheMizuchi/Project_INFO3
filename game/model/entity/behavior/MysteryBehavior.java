package model.entity.behavior;

import model.entity.Entity;
import view.graphicEntity.MysteryView;


public class MysteryBehavior extends MobBehavior {

	MysteryView m_mv;


	public MysteryBehavior (Entity e, MysteryView ev) {
		super(e, ev);
		m_mv = ev;

	}

}
