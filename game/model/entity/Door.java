package model.entity;

import edu.polytech.oop.collections.IList;
import edu.polytech.oop.collections.LinkedList;
import model.entity.behavior.DoorBehavior;
import model.map.generator.Room;
import view.MyCanvas;
import view.graphicEntity.DoorView;


public class Door extends Entity {

	Room m_room;
	Key m_key;
	DoorView m_dv;
	int nb_frame_open;
	DoorBehavior m_db;


	public Door (double x, double y) {
		super(x, y, EntityProperties.DOOR);
		m_tangible = true;
		m_room = null;
		m_key = null;
		m_dv = new DoorView(this);
		m_ev = m_dv;
		m_db = new DoorBehavior(this, m_dv);
		m_eb = m_db;
		MyCanvas.getInstance().createEntityView(m_dv);
	}

	//Ouvrir porte
	@Override
	public void pop () {
		nb_frame_open = 0;
		m_dv.opening();
		m_tangible = false;
	}

	//Fermer porte
	@Override
	public void wizz () {
		m_tangible = true;
		m_dv.closing();
	}

	@Override
	public void store () {

		if (nb_frame_open >= 7) {

			m_dv.opened();
		} else {
			nb_frame_open++;
		}
	}

	public void setRoom (Room r) {
		m_room = r;
	}

	public void setKey (Key k) {
		m_key = k;
	}

	@Override
	public boolean gotStuff () {

		int proximity = 2;

		if (m_key == null) {

			if (distance(J1.getInstance()) > proximity && distance(J2.getInstance()) > proximity) {

				return false;
			}
		} else {

			if (distance(m_key) > proximity) {
				return false;
			}
		}

		LinkedList entities = m_room.getListeEntity();
		IList.Iterator iter = entities.iterator();

		while (iter.hasNext()) {
			Entity e = (Entity) iter.next();

			if (e.getType() == EntityType.ENEMY) {
				return false;
			}
		}

		return true;
	}

}
