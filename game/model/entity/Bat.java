package model.entity;

import model.entity.behavior.BatBehavior;
import model.map.TileType;
import view.MyCanvas;
import view.graphicEntity.BatView;


public class Bat extends Mob {

	BatView m_bv;
	BatBehavior m_bb;
	BatSpawner m_spawner;


	public Bat (double x, double y) {
		super(x, y, EntityProperties.BAT);
		m_bv = new BatView(this);
		m_ev = m_bv;
		m_bb = new BatBehavior(this, m_bv);
		m_mb = m_bb;
		m_eb = m_bb;
		MyCanvas.getInstance().createEntityView(m_bv);
		m_tangible = false;
		m_blockInterdit.remove(TileType.VOID);
	}

	void setSpawner (BatSpawner spawner) {
		m_spawner = spawner;
	}

	@Override
	public void deleteEntity () {
		super.deleteEntity();
		if (m_spawner != null)
			m_spawner.batDied();
	}

}