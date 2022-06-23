package controller.condition;

import controller.BotDirection;
import controller.ICondition;
import model.entity.Entity;
import model.entity.EntityType;
import model.entity.Point;
import model.entity.Vector;


public class BotCell implements ICondition {

	BotDirection m_dir;
	String m_cat;


	public BotCell (String dir, String cat) {
		m_dir = new BotDirection(dir);
		m_cat = cat;
	}

	@Override
	public boolean eval (Entity e) {

		if (m_dir.getSel())
			return e.cell(e.getDirVector(), EntityType.OBSTACLE);

		long environElapsed = 60;

		Vector zer = e.getDirVector().clone();
		zer.setAngle(zer.getAngle() + m_dir.getAngle());
		zer.setX(Math.cos(zer.getAngle()));
		zer.setY(-Math.sin(zer.getAngle()));

		double dx = zer.getX() * e.getMobSpeed() * environElapsed / 1000;
		double dy = zer.getY() * e.getMobSpeed() * environElapsed / 1000;

		Point p1 = new Point(e.m_hitbox.getP1().getX() + dx, e.m_hitbox.getP1().getY() + dy);
		Point p2 = new Point(e.m_hitbox.getP2().getX() + dx, e.m_hitbox.getP2().getY() + dy);
		Point p3 = new Point(e.m_hitbox.getP3().getX() + dx, e.m_hitbox.getP3().getY() + dy);
		Point p4 = new Point(e.m_hitbox.getP4().getX() + dx, e.m_hitbox.getP4().getY() + dy);

		if (e.getHitbox().deplacementValide(p1, p2, p3, p4))
			return true;

		return false;
	}

}
