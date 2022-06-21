package model.entity;

import model.map.generator.Room;


public class Door extends Entity {

	boolean m_isOpen;
	Room m_room;


	public Door (double x, double y, EntityProperties ep, Room room) {
		super(x, y, ep);
		m_isOpen = false;
		m_room = room;
	}

	//Ouvrir porte
	@Override
	public void pop () {
		m_isOpen = true;
	}

	//Fermer porte
	@Override
	public void wizz () {
		m_isOpen = false;
	}

}
