package model.entity;

import edu.polytech.oop.collections.IList;
import edu.polytech.oop.collections.LinkedList;
import model.Model;
import model.map.generator.Room;


public class ClassicDoor extends Door {

	public ClassicDoor (double x, double y) {
		super(x, y, EntityProperties.DOORC);
	}

	//Clef à proximité
	@Override
	public boolean gotStuff () {
		int proximity = 3;
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
