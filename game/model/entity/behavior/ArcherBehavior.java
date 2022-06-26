package model.entity.behavior;

import model.entity.Entity;
import model.entity.Vector;
import view.graphicEntity.ArcherView;


public class ArcherBehavior extends MobBehavior {

	public ArcherBehavior (Entity e, ArcherView ev) {
		super(e, ev);
	}

	@Override
	public void pop () {
		// TODO Archer Pop
	}

	@Override
	public void hit (Vector vec) {
		attackDist(vec, this.e, this);
	}
}
