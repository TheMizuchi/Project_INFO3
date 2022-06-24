package model.entity.behavior;

import model.entity.BatSpawner;
import model.entity.Entity;
import view.graphicEntity.BatSpawnerView;


public class BatSpawnerBehavior extends EntityBehavior {

	BatSpawnerView m_bv;


	public BatSpawnerBehavior (Entity e, BatSpawnerView ev) {
		super(e, ev);
		m_bv = ev;
	}

	@Override
	public void pop () {
		((BatSpawner) this.e).addBats();

	}
}
