package model.entity.behavior;

import model.entity.Doge;
import model.entity.Entity;
import view.graphicEntity.DogeView;


public class DogeBehavior extends MobBehavior {

	public DogeBehavior (Entity e, DogeView ev) {
		super(e, ev);
	}

	@Override
	public void pop () {
		((Doge) this.e).getAngry();
	}
}
