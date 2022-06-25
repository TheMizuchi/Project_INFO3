package model.entity;

import java.awt.Graphics;

import edu.polytech.oop.collections.IList;
import edu.polytech.oop.collections.LinkedList;
import edu.polytech.oop.collections.LinkedList.Iterator;
import model.Model;
import model.map.Case;
import model.map.TileType;


public class Hitbox {

	protected Point m_p1;
	protected Point m_p2;
	protected Point m_p3;
	protected Point m_p4;
	protected Entity m_e;


	public Hitbox (double x, double y, double lar, double haut, Entity e) {
		m_p1 = new Point(x, y);
		m_p2 = new Point(x + lar, y);
		m_p3 = new Point(x + lar, y + haut);
		m_p4 = new Point(x, y + haut);
		m_e = e;
	}

	public Hitbox (Point p1, Point p2, Point p3, Point p4, Entity e) {
		m_p1 = p1;
		m_p2 = p2;
		m_p3 = p3;
		m_p4 = p4;
		m_e = e;
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

	public boolean deplacementValide (Point new_p1, Point new_p2, Point new_p3, Point new_p4) {
		LinkedList tuileInterdit = m_e.getTuileInterdite();
		Iterator it = tuileInterdit.iterator();

		if (new_p1.outMap() || new_p2.outMap() || new_p3.outMap() || new_p4.outMap()) {
			return false;
		}

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

		//if (m_e.isTanguible()) {

		if (contactEntity(new_p1, new_p2, new_p3, new_p4)) {
			return false;
		}
		//}
		return true;
	}

	static public boolean isInsideType (Point p, TileType type) {
		Case[][] grid = Model.getMap().getCases();
		return grid[(int) p.getX()][(int) p.getY()].getType() == type;
	}

	public boolean contactEntity (Point new_p1, Point new_p2, Point new_p3, Point new_p4) {
		IList list = Model.getlistEntity();
		IList.Iterator it = list.iterator();

		Hitbox new_hit = new Hitbox(new_p1, new_p2, new_p3, new_p4, m_e);

		//if (!m_e.isTanguible() && !m_e.isBloon())	
		//	return false;

		while (it.hasNext()) {
			Entity e = (Entity) it.next();

			if ((!e.equal(m_e) && e.isTanguible() && m_e.isTanguible()) || (e.isDoor() && e.isTanguible())) {

				if (e.m_hitbox.pointInHitbox(new_p1) || e.m_hitbox.pointInHitbox(new_p2) || e.m_hitbox.pointInHitbox(new_p3) || e.m_hitbox.pointInHitbox(new_p4) || new_hit.pointInHitbox(e.getHitbox().getP1()) || new_hit.pointInHitbox(e.getHitbox().getP2()) || new_hit.pointInHitbox(e.getHitbox().getP3()) || new_hit.pointInHitbox(e.getHitbox().getP4())) {
					return true;
				}
			}
		}
		return false;
	}

	public void move (Entity e) {
		m_p1 = e.m_hitbox.m_p1;
		m_p2 = e.m_hitbox.m_p2;
		m_p3 = e.m_hitbox.m_p3;
		m_p4 = e.m_hitbox.m_p4;
	}

	public double getCenterX () {
		double dx = (m_p1.getX() + m_p3.getX()) / 2;
		return dx;
	}

	public double getCenterRealX () {
		return (m_p1.getX() + m_p3.getX()) / 2;
	}

	public double getCenterY () {
		double dy = (m_p1.getY() + m_p3.getY()) / 2;
		return dy;
	}

	public double getCenterRealY () {
		return (m_p1.getY() + m_p3.getY()) / 2;
	}

	public boolean pointInHitbox (Point p) {
		return (dansLeTriangle(p, m_p1, m_p2, m_p4) || dansLeTriangle(p, m_p2, m_p3, m_p4));
	}

	boolean dansLeTriangle (Point pt, Point v1, Point v2, Point v3) {
		boolean b1, b2, b3;

		b1 = barycentre(pt, v1, v2) < 0;
		b2 = barycentre(pt, v2, v3) < 0;
		b3 = barycentre(pt, v3, v1) < 0;

		return ((b1 == b2) && (b2 == b3));
	}

	double barycentre (Point p1, Point p2, Point p3) {
		return (p1.getX() - p3.getX()) * (p2.getY() - p3.getY()) - (p2.getX() - p3.getX()) * (p1.getY() - p3.getY());
	}

	public Point getP1 () {
		return m_p1;
	}

	public Point getP2 () {
		return m_p2;
	}

	public Point getP3 () {
		return m_p3;
	}

	public Point getP4 () {
		return m_p4;
	}

	public void paint (Graphics g) {
		m_p1.paint(g);
		m_p2.paint(g);
		m_p3.paint(g);
		m_p4.paint(g);
	}

	public void rotate (double angle) {
		Point center = new Point(getCenterRealX(), getCenterRealY());

		m_p1.sub(center);
		m_p2.sub(center);
		m_p3.sub(center);
		m_p4.sub(center);

		double cos = Math.cos(angle);
		double sin = Math.sin(angle);

		double x = m_p1.getX();
		double y = m_p1.getY();
		m_p1.setX(x * cos - y * sin);
		m_p1.setY(x * sin + y * cos);

		x = m_p2.getX();
		y = m_p2.getY();
		m_p2.setX(x * cos - y * sin);
		m_p2.setY(x * sin + y * cos);

		x = m_p3.getX();
		y = m_p3.getY();
		m_p3.setX(x * cos - y * sin);
		m_p3.setY(x * sin + y * cos);

		x = m_p4.getX();
		y = m_p4.getY();
		m_p4.setX(x * cos - y * sin);
		m_p4.setY(x * sin + y * cos);

		m_p1.add(center);
		m_p2.add(center);
		m_p3.add(center);
		m_p4.add(center);
	}

	public boolean collides (Hitbox h) {
		return this.getP1().getX() < h.getP3().getX() && this.getP3().getX() > h.getP1().getX() && this.getP1().getY() < h.getP3().getY() && this.getP3().getY() > h.getP1().getY();
	}

}