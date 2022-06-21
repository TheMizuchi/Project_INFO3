package model.entity;

import model.map.generator.Room;


public class KeyDoor extends Door {

	Key m_key;


	public KeyDoor (double x, double y, EntityProperties ep, Room room) {
		super(x, y, ep, room);
	}

	//Clef à proximité
	public boolean gotPower () {
		int proximity = 3
		boolean keyCondition = distance(m_key) < proximity;
		
	}

}
