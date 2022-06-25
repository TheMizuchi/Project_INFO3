package model.entity.behavior;

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


public abstract class EntityBehavior {

	Entity e;
	EntityView ev;

	public HurtBox hur;


	public EntityBehavior (Entity e, EntityView ev) {
		this.e = e;
		this.ev = ev;
	}


	// Permet de choisir la précision que vous voulez sur l'angle de MyDir
	static final double MYDIR_SENSI = 15 * 180 / Math.PI;


	public boolean myDir (double orientation, boolean absolute) {
		double angle = (absolute) ? (orientation) : (this.e.getDirVector().getAngle() + orientation);
		return (angle - MYDIR_SENSI <= this.e.getDirVector().getAngle()) && (this.e.getDirVector().getAngle() <= angle + MYDIR_SENSI);
	}

	public boolean cell (Vector vect, EntityType type) {

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

		Point p1 = new Point(this.e.m_hitbox.getP1().getX() + x, this.e.m_hitbox.getP1().getY() + y);
		Point p2 = new Point(this.e.m_hitbox.getP2().getX() + x, this.e.m_hitbox.getP2().getY() + y);
		Point p3 = new Point(this.e.m_hitbox.getP3().getX() + x, this.e.m_hitbox.getP3().getY() + y);
		Point p4 = new Point(this.e.m_hitbox.getP4().getX() + x, this.e.m_hitbox.getP4().getY() + y);

		if (this.e.m_hitbox.deplacementValide(p1, p2, p3, p4))
			return true;

		return false;
	}

	public boolean closest (double orientation, EntityType type) {
		Entity target = e.closest(type);
		return e.distance(target) <= e.getRangeDetection();
	}

	public boolean gotPower () {
		return !(this.e.isDeath());
	}

	public boolean gotStuff () {
		return false;
	}

	public void attack (Entity cible) {}

	public void interact () {}

	abstract public void pop ();

	abstract public void wizz ();

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

		double longeur;

		double dist_x = Math.abs(this.e.m_hitbox.getP1().getX() - this.e.m_hitbox.getP3().getX()) / 2;
		double dist_y = Math.abs(this.e.m_hitbox.getP1().getY() - this.e.m_hitbox.getP3().getY()) / 2;
		double dist_diagonal = Math.sqrt(dist_x * dist_x + dist_y * dist_y);

		if (vec.getX() < 1 && vec.getX() >= a1) {
			longeur = dist_x;
		} else if (vec.getX() < a1 && vec.getX() >= a2) {
			longeur = dist_diagonal;
		} else {
			longeur = dist_y;
		}

		double centre_x = this.e.m_hitbox.getCenterRealX();
		double centre_y = this.e.m_hitbox.getCenterRealY();

		Point p1 = new Point(centre_x - (RANGE_ATTAQUE_PROF + longeur) / 2, centre_y - RANGE_ATTAQUE_LARG / 2);
		Point p4 = new Point(centre_x - (RANGE_ATTAQUE_PROF + longeur) / 2, centre_y + RANGE_ATTAQUE_LARG / 2);

		Point p2 = new Point(centre_x + (RANGE_ATTAQUE_PROF + longeur) / 2, centre_y - RANGE_ATTAQUE_LARG / 2);
		Point p3 = new Point(centre_x + (RANGE_ATTAQUE_PROF + longeur) / 2, centre_y + RANGE_ATTAQUE_LARG / 2);

		HurtBox attaque = new HurtBox(p1, p2, p3, p4, this.e);

		attaque.rotate(vec.getAngle() - (Math.PI / 2));

		double c_p1_p4_x = (attaque.getP1().getX() + attaque.getP4().getX()) / 2;
		double c_p1_p4_y = (attaque.getP1().getY() + attaque.getP4().getY()) / 2;

		attaque.translate(c_p1_p4_x - attaque.getCenterRealX(), c_p1_p4_y - attaque.getCenterRealY());

		attaque.attaque();

	}

	public void protect (Direction orientation) {}

	public void pick () {}

	public void put (Direction orientation) {}

	public void store () {}

	public void get () {}

	public void power () {}

	public void explode () {}

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
