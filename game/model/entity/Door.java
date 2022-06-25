package model.entity;

import java.util.Random;

import edu.polytech.oop.collections.IList;
import edu.polytech.oop.collections.LinkedList;
import model.Model;
import model.entity.behavior.DoorBehavior;
import model.map.generator.Room;
import model.map.generator.RoomType;
import view.MyCanvas;
import view.graphicEntity.DoorView;


public class Door extends Entity {

	Room m_room;
	Key m_key;
	DoorView m_dv;
	public int nb_frame_open;
	DoorBehavior m_db;
	boolean m_stop;


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
		m_stop = false;
	}

	public void setRoom (Room r) {
		m_room = r;
	}

	public void setKey (Key k) {
		m_key = k;
	}

	public boolean shouldIOpenDoor () {

		if (m_stop)
			return false;

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

	public void stops () {
		if (m_room.getVisited())
			return;

		Hitbox j1 = J1.getInstance().getHitbox();
		Hitbox j2 = J2.getInstance().getHitbox();

		boolean j1Inside = m_room.containsHitbox(j1);
		boolean j2Inside = m_room.containsHitbox(j2);

		IList.Iterator iter = m_room.getDoors().iterator();

		if (j1Inside && j2Inside) {

			if (j1.collides(m_room.getFirstDoor().getHitbox()) || j2.collides(m_room.getFirstDoor().getHitbox())) {

			} else {

				while (iter.hasNext()) {
					Door d = (Door) iter.next();
					d.setStop(false);
				}
				m_room.setFirstDoor(null);
				m_room.setVisited(true);
				int nbRandomSpawn = 0;

				if (m_room.getType() == RoomType.FIGHT) {
					Random random = new Random();
					nbRandomSpawn = random.nextInt(10) + 5;
				}
				m_room.spawnEntities(Model.getMap(), nbRandomSpawn);

			}
		} else if (j1Inside || j2Inside) {

			while (iter.hasNext()) {
				Door d = (Door) iter.next();
				d.setStop(true);
			}

			if (j1.collides(this.getHitbox()) || j2.collides(this.getHitbox())) {
				m_room.setFirstDoor(this);
			}

		} else {

			while (iter.hasNext()) {
				Door d = (Door) iter.next();
				d.setStop(false);
			}

			if (j1.collides(this.getHitbox()) || j2.collides(this.getHitbox())) {
				m_room.setFirstDoor(this);
			} else {
				m_room.setFirstDoor(null);
			}
		}
		if (m_room.getFirstDoor() != null)
			m_room.getFirstDoor().setStop(false);
	}

	private void setStop (boolean b) {
		m_stop = b;
	}

}
