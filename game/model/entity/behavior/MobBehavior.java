package model.entity.behavior;

import model.entity.Entity;
import model.entity.Mob;
import view.graphicEntity.EntityView;


public abstract class MobBehavior extends EntityBehavior {

	EntityView ev;


	public MobBehavior (Entity e, EntityView ev) {
		super(e, ev);
		this.ev = ev;
	}

	@Override
	public void wizz () {
		((Mob) this.e).redevientMechant();
	}

	@Override
	abstract public void pop ();

}
