package model.entity.behavior;

import model.Model;
import model.entity.Arrow;
import model.entity.Entity;
import model.entity.EntityProperties;
import model.entity.HurtBox;
import model.entity.Point;
import model.entity.Vector;
import view.graphicEntity.EntityView;


public class J2IceBehavior extends PlayerIceBehavior {

	public J2IceBehavior (Entity e, EntityView ev) {
		super(e, ev);
	}

	@Override
	public void hit (Vector vec) {

		final double RANGE_ATTAQUE_PROF = EntityProperties.ARROW.getWidth();
		final double RANGE_ATTAQUE_LARG = EntityProperties.ARROW.getHeight();

		double a1 = Math.cos(Math.PI * 22.5 / 180);
		double a2 = Math.cos(Math.PI * 67.5 / 180);

		double dx = 0;
		double dy = 0;

		double dist_x = Math.abs(this.e.m_hitbox.getP1().getX() - this.e.m_hitbox.getP3().getX()) / 2;
		double dist_y = Math.abs(this.e.m_hitbox.getP1().getY() - this.e.m_hitbox.getP3().getY()) / 2;

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

		attaque.translate(dx, dy);

		attaque.attaque();

		Arrow a = (Arrow) Model.getInstance().createEntity(0.0, 0.0, EntityProperties.ARROW);
		a.setVector(vec);
		a.m_hitbox = attaque;
		a.setNbDamages(this.e.getNbDamages());
		attaque.setOwner(a);

		ev.attack();
	}

}
