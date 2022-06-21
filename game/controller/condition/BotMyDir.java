package controller.condition;

import controller.ICondition;
import model.entity.Entity;


public class BotMyDir implements ICondition {

	String m_dir;


	public BotMyDir (String dir) {
		m_dir = dir;
	}

	@Override
	public boolean eval (Entity e) {
		throw new RuntimeException("NYI");
	}

}
