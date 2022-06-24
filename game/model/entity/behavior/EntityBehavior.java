package model.entity.behavior;

import edu.polytech.oop.collections.ICollection;
import model.Model;
import model.entity.Direction;
import model.entity.Entity;
import model.entity.EntityProperties;
import model.entity.EntityType;
import model.entity.Hitbox;
import model.entity.HurtBox;
import model.entity.Point;
import model.entity.Vector;
import view.graphicEntity.EntityView;


public class EntityBehavior {

	Entity e;
	EntityView ev;


	public EntityBehavior (Entity e, EntityView ev) {
		this.e = e;
		this.ev = ev;
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
		Entity e, e_min = null;
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
		if (e_min == null)
			return false;
		// Si y a une erreur dans closest, elle est dans cette détection d'angle
		if (this.e.angleVers(e_min) < this.e.getDirVector().getAngle() + 0.2 && this.e.angleVers(e_min) > this.e.getDirVector().getAngle() - 0.2)
			return true;
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

		final double RANGE_ATTAQUE_PROF = 0.5;
		final double RANGE_ATTAQUE_LARG = 1;

		double a1 = Math.cos(Math.PI * 22.5 / 180);
		double a2 = Math.cos(Math.PI * 67.5 / 180);

		double profondeur;

		double dist_x = Math.abs(this.e.m_hitbox.getP1().getX() - this.e.m_hitbox.getP3().getX()) / 2;
		double dist_y = Math.abs(this.e.m_hitbox.getP1().getY() - this.e.m_hitbox.getP3().getY()) / 2;
		double dist_diagonal = Math.sqrt(dist_x * dist_x + dist_y * dist_y);

		if (Math.abs(vec.getX()) < 1 && Math.abs(vec.getX()) >= a1) {
			profondeur = dist_x;
		} else if (Math.abs(vec.getX()) < a1 && Math.abs(vec.getX()) >= a2) {
			profondeur = dist_diagonal;
		} else {
			profondeur = dist_y;
		}

		double centre_x = this.e.m_hitbox.getCenterRealX();
		double centre_y = this.e.m_hitbox.getCenterRealY();

		double largeur_hurt_box = RANGE_ATTAQUE_PROF + profondeur;
		double hauteur_hurt_box = RANGE_ATTAQUE_LARG;

		Point p1 = new Point(centre_x - (largeur_hurt_box / 2), centre_y - (hauteur_hurt_box / 2));
		Point p2 = new Point(centre_x + (largeur_hurt_box / 2), centre_y - (hauteur_hurt_box / 2));
		Point p3 = new Point(centre_x + (largeur_hurt_box / 2), centre_y + (hauteur_hurt_box / 2));
		Point p4 = new Point(centre_x - (largeur_hurt_box / 2), centre_y + (hauteur_hurt_box / 2));

		HurtBox attaque = new HurtBox(p1, p2, p3, p4, this.e);

		attaque.rotate(vec.getAngle());

		double c_p1_p4_x = (attaque.getP1().getX() + attaque.getP4().getX()) / 2;
		double c_p1_p4_y = (attaque.getP1().getY() + attaque.getP4().getY()) / 2;

		attaque.translate(c_p1_p4_x - attaque.getCenterRealX(), c_p1_p4_y - attaque.getCenterRealY());

		System.out.println("angle " + vec.getAngle());

		System.out.println("je suis en : " + this.e.getHibox().getP1() + " " + this.e.getHibox().getP2() + " " + this.e.getHibox().getP3() + "\n" + this.e.getHibox().getP4() + " ");

		System.out.println("attaque en : " + attaque.getP1() + " " + attaque.getP2() + " " + attaque.getP3() + "\n" + attaque.getP4());

		System.out.println("centre hurtbox : y " + attaque.getCenterRealY() + "x = " + attaque.getP1().getX() + " ou " + attaque.getP3().getX());

		System.out.println("centre hitbox : y : " + this.e.getHibox().getCenterRealY() + " x :" + this.e.getHibox().getCenterRealX() + "\n\n\n\n");

		attaque.attaque();

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
