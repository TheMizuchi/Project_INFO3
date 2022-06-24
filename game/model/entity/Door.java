package model.entity;

import edu.polytech.oop.collections.IList;
import edu.polytech.oop.collections.LinkedList;
import model.Model;
import model.entity.behavior.DoorBehavior;
import model.map.generator.Room;
import view.MyCanvas;
import view.graphicEntity.DoorView;


public class Door extends Entity {

	Room m_room;
	Key m_key;
	DoorView m_dv;
	public int nb_frame_open;
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
		m_db.pop();
	}

	//Fermer porte
	@Override
	public void wizz () {
		m_db.wizz();
	}

	@Override
	public void store () {
		m_db.store();

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

		System.out.println("HEY");

		if (m_key == null) {

			if (distance(J1.getInstance()) > proximity && distance(J2.getInstance()) > proximity) {

				return false;
			}
		} else {

			if (distance(m_key) > proximity) {
				return false;
			}
		}

		LinkedList entities = (LinkedList) Model.getlistEntity();
		IList.Iterator iter = entities.iterator();

		while (iter.hasNext()) {
			Entity e = (Entity) iter.next();

			if (e.getType() == EntityType.ENEMY) {
				return false;
			}
		}

		return true;
	}

	@Override
	void takeDamages (int damages) {
		return;
	}

	public void setTangible (boolean b) {
		m_tangible = b;
	}

}
