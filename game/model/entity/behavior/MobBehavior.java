package model.entity.behavior;

import model.entity.Entity;
import model.entity.Mob;
import view.graphicEntity.EntityView;


public abstract class MobBehavior extends EntityBehavior {

	public MobBehavior (Entity e, EntityView ev) {
		super(e, ev);
	}

	@Override
	public void wizz () {
		((Mob) this.e).redevientMechant();
	}

	@Override
	abstract public void pop ();

}
