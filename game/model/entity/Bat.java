package model.entity;

import view.MyCanvas;
import view.graphicEntity.BatView;


public class Bat extends Mob {

	BatView m_bv;
	BatSpawner m_spawner;


	public Bat (double x, double y) {
		super(x, y, EntityProperties.BAT);
		m_bv = new BatView(this);
		m_ev = m_bv;
		MyCanvas.getInstance().createEntityView(m_bv);
		m_tangible = false;
	}

	//Constructeur pour créer entité sans view
	public Bat (double x, double y, Object o) {
		super(x, y, EntityProperties.BAT);
	}

	void setSpawner (BatSpawner spawner) {
		m_spawner = spawner;
	}

	@Override
	public void deleteEntity () {
		super.deleteEntity();
		m_spawner.batDied();
	}

}