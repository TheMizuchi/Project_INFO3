package model.entity.behavior;

import model.entity.Doge;
import model.entity.Entity;
import model.entity.Vector;
import view.graphicEntity.DogeView;


public class DogeBehavior extends MobBehavior {

	public DogeBehavior (Entity e, DogeView ev) {
		super(e, ev);
	}

	@Override
	public void pop () {
		((Doge) this.e).getAngry();
	}

	@Override
	public void hit (Vector vec) {
		attackDist(vec, e, false);
		vec.setAngle(vec.getAngle() - (Math.PI / 4));
		attackDist(vec, e, false);
		vec.setAngle(vec.getAngle() + (Math.PI / 2));
		attackDist(vec, e, false);

	}
}
