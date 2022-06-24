package model.entity.behavior;

import model.entity.Entity;
import model.entity.Mob;
import view.graphicEntity.EntityView;


public class MobBehavior extends EntityBehavior {

	EntityView ev;


	public MobBehavior (Entity e, EntityView ev) {
		super(e, ev);
		this.ev = ev;
	}

	@Override
	public void wizz () {
		((Mob) this.e).redevientMechant();
	}

}
