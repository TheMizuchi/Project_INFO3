package model.entity;

import edu.polytech.oop.collections.IList;
import edu.polytech.oop.collections.LinkedList;
import model.map.generator.Room;


public class KeyDoor extends Door {

	Key m_key;


	public KeyDoor (double x, double y) {
		super(x, y, EntityProperties.DOORKEY);
	}

	public boolean gotStuff () {
		int proximity = 3;
		if (distance(m_key) > proximity)
			return false;

		LinkedList entities = m_room.getListeEntity();
		IList.Iterator iter = entities.iterator();

		while (iter.hasNext()) {
			Entity e = (Entity) iter.next();
			if (e.getType() == EntityType.ENEMY)
				return false;
		}
		//Si ça marche pas, c'est sûrement parce qu'on a supposé que [clef proche => J proche]
		return true;
	}

}
