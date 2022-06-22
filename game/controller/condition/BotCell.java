package controller.condition;

import controller.BotDirection;
import controller.ICondition;
import model.entity.Entity;
import model.entity.EntityType;


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

		double dx = Math.cos(m_dir.getAngle());
		double dy = Math.sin(m_dir.getAngle());
		if (e.getHitbox().deplacementValide(e.getPosX() + dx, e.getPosY() - dy))
			return true;
		return false;
	}

}
