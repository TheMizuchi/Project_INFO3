package model.entity;

import edu.polytech.oop.collections.IList;
import edu.polytech.oop.collections.LinkedList;
import edu.polytech.oop.collections.LinkedList.Iterator;
import model.Model;
import model.map.TileType;


public class HurtBox extends Hitbox {

	public HurtBox (double x, double y, double lar, double haut, Entity e) {
		super(x, y, lar, haut, e);
	}

	public HurtBox (Point p1, Point p2, Point p3, Point p4, Entity e) {
		super(p1, p2, p3, p4, e);
	}

	public void move (double dx, double dy) {
		Point new_p1_x = new Point(m_p1.getX() + dx, m_p1.getY());
		Point new_p2_x = new Point(m_p2.getX() + dx, m_p2.getY());
		Point new_p3_x = new Point(m_p3.getX() + dx, m_p3.getY());
		Point new_p4_x = new Point(m_p4.getX() + dx, m_p4.getY());

		if (deplacementValide(new_p1_x, new_p2_x, new_p3_x, new_p4_x)) {
			m_p1 = new_p1_x;
			m_p2 = new_p2_x;
			m_p3 = new_p3_x;
			m_p4 = new_p4_x;
		}

		Point new_p1_y = new Point(m_p1.getX(), m_p1.getY() + dy);
		Point new_p2_y = new Point(m_p2.getX(), m_p2.getY() + dy);
		Point new_p3_y = new Point(m_p3.getX(), m_p3.getY() + dy);
		Point new_p4_y = new Point(m_p4.getX(), m_p4.getY() + dy);

		if (deplacementValide(new_p1_y, new_p2_y, new_p3_y, new_p4_y)) {
			m_p1 = new_p1_y;
			m_p2 = new_p2_y;
			m_p3 = new_p3_y;
			m_p4 = new_p4_y;
		}
	}

	public void attaque () {
		IList list = Model.getlistEntity();
		IList.Iterator it = list.iterator();

		while (it.hasNext()) {
			Entity e = (Entity) it.next();

			if (!e.equal(m_e)) {

				if ((e.m_hitbox.pointInHitbox(m_p1) || e.m_hitbox.pointInHitbox(m_p2) || e.m_hitbox.pointInHitbox(m_p3) || e.m_hitbox.pointInHitbox(m_p4))) {
					m_e.attack(e);
				}
			}
		}
	}

	@Override
	public boolean deplacementValide (Point new_p1, Point new_p2, Point new_p3, Point new_p4) {
		LinkedList tuileInterdit = m_e.getTuileInterdite();
		Iterator it = tuileInterdit.iterator();

		while (it.hasNext()) {
			TileType tile = (TileType) it.next();

			if (isInsideType(new_p1, tile)) {
				return false;
			}

			if (isInsideType(new_p2, tile)) {
				return false;
			}

			if (isInsideType(new_p3, tile)) {
				return false;
			}

			if (isInsideType(new_p4, tile)) {
				return false;
			}
		}
		return true;
	}

	public void translate (double dx, double dy) {
		Point p = new Point(dx, dy);
		m_p1.add(p);
		m_p2.add(p);
		m_p3.add(p);
		m_p4.add(p);
	}

}
