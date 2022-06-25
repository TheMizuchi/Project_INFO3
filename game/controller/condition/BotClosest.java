package controller.condition;

import controller.BotCategory;
import controller.BotDirection;
import controller.ICondition;
import model.entity.Entity;


public class BotClosest implements ICondition {

	BotDirection m_dir;
	BotCategory m_cat;


	public BotClosest (String dir, String cat) {
		m_dir = new BotDirection(dir);
		m_cat = new BotCategory(cat);
	}

	@Override
	public boolean eval (Entity e) {
		return e.closest(m_dir.getAngle(), m_cat.getType());
	}

}
