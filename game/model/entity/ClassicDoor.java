package model.entity;

import edu.polytech.oop.collections.IList;
import edu.polytech.oop.collections.LinkedList;
import model.Model;
import model.map.generator.Room;


public class ClassicDoor extends Door {

	public ClassicDoor (double x, double y, EntityProperties ep, Room room) {
		super(x, y, ep, room);
	}

	//Clef à proximité
	@Override
	public boolean gotPower () {
		int proximity = 3;
		Model model = Model.getInstance();
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
