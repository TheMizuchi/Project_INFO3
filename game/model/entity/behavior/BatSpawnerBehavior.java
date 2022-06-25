package model.entity.behavior;

import model.entity.BatSpawner;
import model.entity.Entity;
import view.graphicEntity.BatSpawnerView;


public class BatSpawnerBehavior extends EntityBehavior {

	public BatSpawnerBehavior (Entity e, BatSpawnerView ev) {
		super(e, ev);
	}

	@Override
	public void pop () {
		((BatSpawner) this.e).addBats();
	}

	@Override
	public void wizz () {
		e.waitt(5000);
	}
}
