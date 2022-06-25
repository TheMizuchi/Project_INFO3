package model.entity.behavior;

import model.entity.Entity;
import view.graphicEntity.BatView;


public class BatBehavior extends MobBehavior {

	BatView m_bv;


	public BatBehavior (Entity e, BatView ev) {
		super(e, ev);
		m_bv = ev;
	}

	@Override
	public void pop () {
		// TODO Bat Pop
	}
}
