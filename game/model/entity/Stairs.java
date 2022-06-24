package model.entity;

import edu.polytech.oop.collections.IList;
import edu.polytech.oop.collections.LinkedList;
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

	public boolean gotStuff () {

		int proximity = 1;

		if (distance(J1.getInstance()) > proximity && (distance(J2.getInstance()) > proximity))
			return false;

		LinkedList entities = (LinkedList) Model.getlistEntity();
		IList.Iterator iter = entities.iterator();

		while (iter.hasNext()) {
			Entity e = (Entity) iter.next();
			if (e.getType() == EntityType.ENEMY)
				return false;
		}
		return true;
	}

	@Override
	void takeDamages (int damages) {
		return;
	}

}
