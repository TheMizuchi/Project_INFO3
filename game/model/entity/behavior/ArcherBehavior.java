package model.entity.behavior;

import model.entity.Entity;
import view.graphicEntity.ArcherView;


public class ArcherBehavior extends MobBehavior {

	ArcherView m_av;


	public ArcherBehavior (Entity e, ArcherView ev) {
		super(e, ev);
		m_av = ev;
	}

	@Override
	public void pop () {
		// TODO Archer Pop
	}
}
