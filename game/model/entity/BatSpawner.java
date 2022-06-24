package model.entity;

import model.Model;
import model.entity.behavior.BatSpawnerBehavior;
import view.MyCanvas;
import view.graphicEntity.BatSpawnerView;


public class BatSpawner extends Entity {

	BatSpawnerView m_bsv;
	BatSpawnerBehavior m_bsb;
	int m_bats;


	public BatSpawner (double x, double y) {
		super(x, y, EntityProperties.BATSPAWNER);
		m_bsv = new BatSpawnerView(this);
		m_ev = m_bsv;
		m_bsb = new BatSpawnerBehavior(this, m_bsv);
		m_eb = m_bsb;
		MyCanvas.getInstance().createEntityView(m_bsv);
	}

	public void addBats () {

		if (m_bats < 5) {
			Bat bat = (Bat) Model.getInstance().createEntity(getPosX(), getPosY(), EntityProperties.BAT);
			bat.setSpawner(this);
			m_bats++;
		}
	}

	void batDied () {
		m_bats--;
	}

	@Override
	void takeDamages (int damages) {
		return;
	}

}
