package controller.condition;

import controller.ICondition;
import model.entity.Entity;
import model.entity.EntityType;


public class BotCell implements ICondition {

	String m_dir;
	String m_cat;


	public BotCell (String dir, String cat) {
		m_dir = dir;
		m_cat = cat;
	}

	@Override
	public boolean eval (Entity e) {
		return e.cell(e.getDirVector(), EntityType.OBSTACLE);
	}

}
