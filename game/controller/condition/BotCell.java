package controller.condition;

import controller.BotCategory;
import controller.BotDirection;
import controller.ICondition;
import model.Model;
import model.entity.Entity;
import model.entity.EntityProperties;
import model.entity.EntityType;
import model.entity.Hitbox;
import model.entity.Point;
import model.entity.Vector;
import model.map.TileType;


public class BotCell implements ICondition {

	BotDirection m_dir;
	BotCategory m_cat;


	public BotCell (String dir, String cat) {
		m_dir = new BotDirection(dir);
		m_cat = new BotCategory(cat);
	}

	@Override
	public boolean eval (Entity e) {

		long environElapsed = Model.getInstance().getLastElapsed();

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

		if (m_cat.getType() == EntityType.OBSTACLE) {
			boolean wall = Hitbox.isInsideType(p1, TileType.WALL) || Hitbox.isInsideType(p2, TileType.WALL) || Hitbox.isInsideType(p3, TileType.WALL) || Hitbox.isInsideType(p4, TileType.WALL);

			Entity closestObstacle = e.closest(EntityType.OBSTACLE);
			boolean door = false;

			if (closestObstacle.getProperties() == EntityProperties.DOOR) {
				door = closestObstacle.m_hitbox.collides(p1, p2, p3, p4);
			}
			return wall || door;
		} else {
			Entity target = e.closest(m_cat.getType());
			if (target.m_hitbox.collides(p1, p2, p3, p4))
				return true;
		}
		return false;
	}

}
