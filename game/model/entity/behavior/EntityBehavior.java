package model.entity.behavior;

import edu.polytech.oop.collections.ICollection;
import model.Model;
import model.entity.Direction;
import model.entity.Entity;
import model.entity.EntityProperties;
import model.entity.EntityType;
import model.entity.Hitbox;
import model.entity.Point;
import model.entity.Vector;


public class EntityBehavior {

	Entity e;


	public EntityBehavior (Entity e) {
		this.e = e;
	}

	public void attack (Entity cible) {}

	public void interact () {}


	// Permet de choisir la précision que vous voulez sur l'angle de MyDir
	static final double MYDIR_SENSI = 15 * 180 / Math.PI;


	public boolean myDir (double orientation, boolean absolute, Vector vecDir) {
		double angle = (absolute) ? (orientation) : (vecDir.getAngle() + orientation);
		return (angle - MYDIR_SENSI <= vecDir.getAngle()) && (vecDir.getAngle() <= angle + MYDIR_SENSI);
	}

	public boolean cell (Vector vect, EntityType type, Hitbox hitbox) {

		float x = 0, y = 0;
		double ang = vect.getAngle();

		if (ang >= 7 * Math.PI / 4) {
			x = 1;
			y = 0;
		}

		if (ang < 7 * Math.PI / 4) {
			x = 0;
			y = 1;
		}

		if (ang < 5 * Math.PI / 4) {
			x = -1;
			y = 0;
		}

		if (ang < 3 * Math.PI / 4) {
			x = 0;
			y = -1;
		}

		if (ang < Math.PI / 4) {
			x = 1;
			y = 0;
		}

		Point p1 = new Point(hitbox.getP1().getX() + x, hitbox.getP1().getY() + y);
		Point p2 = new Point(hitbox.getP2().getX() + x, hitbox.getP2().getY() + y);
		Point p3 = new Point(hitbox.getP3().getX() + x, hitbox.getP3().getY() + y);
		Point p4 = new Point(hitbox.getP4().getX() + x, hitbox.getP4().getY() + y);

		if (hitbox.deplacementValide(p1, p2, p3, p4))
			return true;

		return false;
	}

	public boolean closest (Direction orientation, EntityType type, double rangeDetection) {
		ICollection.Iterator iter = Model.getlistEntity().iterator();
		Entity e, e_min;
		double distMin = Float.MAX_VALUE;

		while (iter.hasNext()) {
			e = (Entity) iter.next();

			if (e.getType() == type) {
				double dist = this.e.distance(e);

				if (distMin > dist && distMin < rangeDetection) {
					e_min = e;
					distMin = dist;
				}
			}
		}
		// TODO
		// à implémenter lorsque les directions de dova et diego sont stables
		//if (e_min.position in range of orientation)^
		//	return true;
		return false;
	}

	public Entity closest (EntityType type) {
		ICollection.Iterator iter = Model.getlistEntity().iterator();
		Entity e, e_min = null;
		double distMin = Double.MAX_VALUE;

		while (iter.hasNext()) {
			e = (Entity) iter.next();

			if (e.getType() == type) {
				double dist = this.e.distance(e);

				if (distMin > dist) {
					e_min = e;
					distMin = dist;
				}

			}
		}
		return e_min;
	}

	public Entity closest (boolean possessable) {
		ICollection.Iterator iter = Model.getlistEntity().iterator();
		Entity e, e_min = null;
		double distMin = Double.MAX_VALUE;

		while (iter.hasNext()) {
			e = (Entity) iter.next();

			if (e.getPossessable() == possessable) {
				double dist = this.e.distance(e);

				if (distMin > dist) {
					e_min = e;
					distMin = dist;
				}

			}
		}
		return e_min;
	}

	public boolean gotPower (int pv) {
		return pv > 0;
	}

	public boolean gotStuff () {
		// TODO Auto-generated method stub
		return false;
	}

	public void pop () {
		// TODO C'est un truc à faire ça 
	}

	public void wizz () {
		// TODO Auto-generated method stub

	}

	public boolean move (Direction dir, Vector vecDir) {
		return dir.move(vecDir);
	}

	public void turn (double orientation, boolean absolute, Vector vecDir) {
		vecDir.setAngle((absolute) ? (orientation) : (vecDir.getAngle() + orientation));
	}

	public void hit (Vector vec) {

	}

	public void protect (Direction orientation) {
		// TODO Auto-generated method stub

	}

	public void pick () {
		// TODO Auto-generated method stub

	}

	public void put (Direction orientation) {
		// TODO Auto-generated method stub

	}

	public void store () {
		// TODO Auto-generated method stub

	}

	public void get () {
		// TODO Auto-generated method stub

	}

	public void power () {
		// TODO Auto-generated method stub

	}

	public void explode () {
		// TODO Auto-generated method stub

	}

	public void egg (double orientationx, double orientationy, Hitbox hitbox, EntityProperties entityProperties) {
		Model m;
		m = Model.getInstance();

		Point p1 = new Point(hitbox.getP1().getX() + orientationx, hitbox.getP1().getY() + orientationy);
		Point p2 = new Point(hitbox.getP2().getX() + orientationx, hitbox.getP2().getY() + orientationy);
		Point p3 = new Point(hitbox.getP3().getX() + orientationx, hitbox.getP3().getY() + orientationy);
		Point p4 = new Point(hitbox.getP4().getX() + orientationx, hitbox.getP4().getY() + orientationy);

		if (hitbox.deplacementValide(p1, p2, p3, p4)) {
			Entity e = m.createEntity(this.e.getPosX() + orientationx, this.e.getPosY() - orientationy, entityProperties);
			m.createLightSource(e);
		}
	}
}
