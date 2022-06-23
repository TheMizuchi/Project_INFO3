package model.entity;

import edu.polytech.oop.collections.IList;
import edu.polytech.oop.collections.LinkedList;
import model.Model;
import model.map.Map;
import model.map.generator.Room;


public class Stairs extends Entity {

	Room m_room;


	public Stairs (double x, double y) {
		super(x, y, EntityProperties.STAIRS);
		m_tangible = false;
		Model model = Model.getInstance();
		Map m = model.getMap();
		m_room = m.getBoss();
	}

	//Changer d'étage
	public void pop () {

	}

	public boolean gotStuff () {

		int proximity = 1;

		if (distance(J1.getInstance()) > proximity && distance(J2.getInstance()) > proximity)
			return false;

		LinkedList entities = m_room.getListeEntity();
		IList.Iterator iter = entities.iterator();

		while (iter.hasNext()) {
			Entity e = (Entity) iter.next();
			if (e.getType() == EntityType.ENEMY)
				return false;
		}

		return true;
	}

}
