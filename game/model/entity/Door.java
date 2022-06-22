package model.entity;

import edu.polytech.oop.collections.IList;
import edu.polytech.oop.collections.LinkedList;
import model.map.generator.Room;


public class Door extends Entity {

	Room m_room;
	Key m_key;


	public Door (double x, double y, EntityProperties ep) {
		super(x, y, ep);
		m_room = null;
		m_tangible = true;
	}

	//Ouvrir porte
	@Override
	public void pop () {
		m_tangible = false;
	}

	//Fermer porte
	@Override
	public void wizz () {
		m_tangible = true;
	}

	public void setRoom (Room r) {
		m_room = r;
	}

	@Override
	public boolean gotStuff () {
		int proximity = 3;

		if (m_key == null) {
			if (distance(J1.getInstance()) > proximity && distance(J2.getInstance()) > proximity)
				return false;
		} else {
			if (distance(m_key) > proximity)
				return false;
		}

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
