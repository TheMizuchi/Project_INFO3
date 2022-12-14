package model.entity.behavior;

import model.entity.Entity;
import model.entity.Vector;
import view.graphicEntity.DogeView;


public class AngryDogeBehavior extends DogeBehavior {

	public AngryDogeBehavior (Entity e, DogeView ev) {
		super(e, ev);
	}

	@Override
	public void pop () {}

	@Override
	public void hit (Vector vec) {
		attackCac(vec, e);
	}
}
