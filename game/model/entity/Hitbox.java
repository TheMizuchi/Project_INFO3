package model.entity;

import edu.polytech.oop.collections.IList;
import edu.polytech.oop.collections.LinkedList;
import edu.polytech.oop.collections.LinkedList.Iterator;
import model.Model;
import model.map.Case;
import model.map.TileType;


public class Hitbox {

	private double m_x;
	private double m_y;
	private double m_largeur;
	private double m_hauteur;
	private Entity m_e;


	public Hitbox (double x, double y, double lar, double haut, Entity e) {
		m_x = x;
		m_y = y;
		m_largeur = lar;
		m_hauteur = haut;
		m_e = e;
	}

	public void move (double dx, double dy) {

		if (deplacementValide(m_x + dx, m_y)) {
			m_x += dx;
		}

		if (deplacementValide(m_x, m_y + dy)) {
			m_y += dy;
		}
	}

	public boolean colisionWithType (double x, double y, TileType type) {
		Case[][] grid = Model.getInstance().getMap().getCases();

		if (grid[(int) x][(int) y].getType() == type) {
			return true;
		}

		if (grid[(int) (x + m_largeur)][(int) y].getType() == type) {
			return true;
		}

		if (grid[(int) x][(int) (y + m_hauteur)].getType() == type) {
			return true;
		}

		if (grid[(int) (x + m_largeur)][(int) (y + m_hauteur)].getType() == type) {
			return true;
		}
		return false;
	}

	public boolean deplacementValide (double x, double y) {
		LinkedList tuileInterdit = m_e.getTuileInterdite();
		Iterator it = tuileInterdit.iterator();

		while (it.hasNext()) {

			if (colisionWithType(x, y, (TileType) it.next())) {
				return false;
			}
		}

		if (m_e.isTanguible()) {

			if (contactEntity(x, y)) {
				return false;
			}
		}
		return true;
	}

	public boolean contactEntity (double x, double y) {
		IList list = Model.getlistEntity();
		IList.Iterator it = list.iterator();

		while (it.hasNext()) {
			Entity e = (Entity) it.next();

			if (!e.equals(m_e) && e.isTanguible()) {

				if (this.conflitHitbox(x, y, (e).getHibox())) {
					return true;
				}
			}
		}
		return false;
	}

	public void move (EntityInterface e) {
		m_x = e.getPosX();
		m_y = e.getPosY();
	}

	public double getX () {
		return m_x;
	}

	public double getCenterX () {
		return m_x + (m_largeur / 2);
	}

	public double getY () {
		return m_y;
	}

	public double getCenterY () {
		return m_y + (m_hauteur / 2);
	}

	public double getLargeur () {
		return m_largeur;
	}

	public double getHauteur () {
		return m_hauteur;
	}

	public boolean conflitHitbox (double x, double y, Hitbox hitb) {
		double dx = (m_x - x) * 1;
		double dy = (m_y - y) * 1;

		double hitx = hitb.getX();
		double hity = hitb.getY();
		double hitxl = hitx + hitb.getLargeur();
		double hityh = hity + hitb.getHauteur();

		return (this.pointInHitbox(hitx + dx, hity + dy) || this.pointInHitbox(hitxl + dx, hity + dy) || this.pointInHitbox(hitx + dx, hityh + dy) || this.pointInHitbox(hitxl + dx, hityh + dy) || hitb.pointInHitbox(m_x - dx, m_y - dy) || hitb.pointInHitbox(m_x - dx + m_largeur, m_y - dy) || hitb.pointInHitbox(m_x - dx, m_y + m_hauteur - dy) || hitb.pointInHitbox(m_x + m_largeur - dx, m_y + m_hauteur - dy));
	}

	public boolean pointInHitbox (double x, double y) {
		return ((x >= m_x) && (m_x + m_largeur >= x)) && ((y >= m_y) && (m_y + m_hauteur >= y));
	}

}