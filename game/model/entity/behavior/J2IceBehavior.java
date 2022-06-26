package model.entity.behavior;

import model.entity.Entity;
import model.entity.Vector;
import view.graphicEntity.EntityView;


public class J2IceBehavior extends PlayerIceBehavior {

	public J2IceBehavior (Entity e, EntityView ev) {
		super(e, ev);
	}

	@Override
	public void hit (Vector vec) {
		attackDist(vec, this.e, true);
		ev.attack();
	}
}
