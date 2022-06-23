package model.entity;

import model.Model;
import view.MyCanvas;
import view.graphicEntity.BatSpawnerView;


public class BatSpawner extends Entity {

	BatSpawnerView m_bsv;
	int m_bats;


	public BatSpawner (double x, double y) {
		super(x, y, EntityProperties.BATSPAWNER);
		m_bsv = new BatSpawnerView(this);
		m_ev = m_bsv;
		MyCanvas.getInstance().createEntityView(m_bsv);
	}

	@Override
	public void pop () {

		if (m_bats < 5) {
			Bat bat = (Bat) Model.getInstance().createEntity(getPosX(), getPosY(), EntityProperties.BAT);
			bat.setSpawner(this);
			m_bats++;
		}
	}

	void batDied () {
		m_bats--;
	}

}
