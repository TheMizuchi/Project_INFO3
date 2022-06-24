package model.entity.behavior;

import model.Model;
import model.entity.Direction;
import model.entity.Entity;
import model.entity.EntityProperties;
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
