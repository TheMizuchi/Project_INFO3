package model.entity.behavior;

import model.Model;
import model.entity.Arrow;
import model.entity.Entity;<<<<<<<HEAD
import model.entity.EntityProperties;=======
import model.entity.HurtBox;
import model.entity.Point;>>>>>>>master
import model.entity.Vector;
import view.graphicEntity.EntityView;


public class J2NormalGroundBehavior extends PlayerNormalGroundBehavior {

	public J2NormalGroundBehavior (Entity e, EntityView ev) {
		super(e, ev);
	}

	@Override
	public void hit (Vector vec) {

<<<<<<< HEAD
		Model model = Model.getInstance();
		double eWidth = this.e.getProperties().getWidth();
		double eHeight = this.e.getProperties().getHeight();
		double aWidth = EntityProperties.ARROW.getWidth();
		double aHeight = EntityProperties.ARROW.getHeight();
		double eSemi_diagonal = 0.5 * Math.sqrt(eWidth * eWidth + eHeight * eHeight);
		double aSemi_diagonal = 0.5 * Math.sqrt(aWidth * aWidth + aHeight * aHeight);

		double eCenterX = this.e.getHitbox().getCenterRealX();
		double eCenterY = this.e.getHitbox().getCenterRealY();
		double aCenterX = eCenterX + (eSemi_diagonal + aSemi_diagonal + 0.1) * Math.cos(vec.getAngle());
		double aCenterY = eCenterY + (eSemi_diagonal + aSemi_diagonal + 0.1) * Math.sin(vec.getAngle());
		double aX = aCenterX - aWidth / 2;
		double aY = aCenterY - aHeight / 2;

		Arrow a = (Arrow) model.createEntity(aX, aY, EntityProperties.ARROW);
		a.setVector(vec);
		a.
=======
		final double RANGE_ATTAQUE_PROF = 0.5;
		final double RANGE_ATTAQUE_LARG = 0.2;

		double a1 = Math.cos(Math.PI * 22.5 / 180);
		double a2 = Math.cos(Math.PI * 67.5 / 180);

		double dx = 0;
		double dy = 0;

		double dist_x = Math.abs(this.e.m_hitbox.getP1().getX() - this.e.m_hitbox.getP3().getX()) / 2;
		double dist_y = Math.abs(this.e.m_hitbox.getP1().getY() - this.e.m_hitbox.getP3().getY()) / 2;
		double dist_diagonal = Math.sqrt(dist_x * dist_x + dist_y * dist_y);

		System.out.println("vec x : " + vec.getX());

		if (Math.abs(vec.getX()) <= 1 && Math.abs(vec.getX()) >= a1) {
			dx = dist_x + RANGE_ATTAQUE_PROF / 2;

			if (vec.getX() < 0) {
				dx = -dx;
			}
		} else if (Math.abs(vec.getX()) < a1 && Math.abs(vec.getX()) >= a2) {
			dx = dist_x + RANGE_ATTAQUE_PROF / 2;
			dy = dist_y + RANGE_ATTAQUE_LARG / 2;

			if (vec.getX() < 0) {
				dx = -dx;
			}

			if (vec.getY() < 0) {
				dy = -dy;
			}
		} else {
			dy = dist_y + RANGE_ATTAQUE_PROF / 2;

			if (vec.getY() < 0) {
				dy = -dy;
			}
		}

		double centre_x = this.e.m_hitbox.getCenterRealX();
		double centre_y = this.e.m_hitbox.getCenterRealY();

		Point p1 = new Point(centre_x - (RANGE_ATTAQUE_PROF / 2), centre_y - (RANGE_ATTAQUE_LARG / 2));
		Point p2 = new Point(centre_x + (RANGE_ATTAQUE_PROF / 2), centre_y - (RANGE_ATTAQUE_LARG / 2));
		Point p3 = new Point(centre_x + (RANGE_ATTAQUE_PROF / 2), centre_y + (RANGE_ATTAQUE_LARG / 2));
		Point p4 = new Point(centre_x - (RANGE_ATTAQUE_PROF / 2), centre_y + (RANGE_ATTAQUE_LARG / 2));

		HurtBox attaque = new HurtBox(p1, p2, p3, p4, this.e);

		attaque.rotate(-vec.getAngle());

		this.hur = attaque;

		System.out.println("dx : " + dx);

		attaque.translate(dx, dy);

		attaque.attaque();

		ev.attack();

>>>>>>> master
	}

}
