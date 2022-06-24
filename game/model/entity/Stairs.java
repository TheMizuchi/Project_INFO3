package model.entity;

import model.Model;
import model.entity.behavior.StairsBehavior;
import model.map.generator.Room;
import view.graphicEntity.StairsView;


public class Stairs extends Entity {

	Room m_room;
	StairsView m_sv;
	StairsBehavior m_sb;


	public Stairs (double x, double y) {
		super(x, y, EntityProperties.STAIRS);
		m_tangible = false;
		m_sb = new StairsBehavior(this, m_sv);
		m_eb = m_sb;
		m_room = Model.getMap().getBoss();
	}

	public Room getRoom () {
		return m_room;
	}

	public boolean shouldIEnterStairs () {
		return m_sb.gotStuff();
	}

	@Override
	void takeDamages (int damages) {
		return;
	}

}
