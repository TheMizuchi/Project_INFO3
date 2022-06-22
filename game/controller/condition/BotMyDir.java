package controller.condition;

import controller.BotDirection;
import controller.ICondition;
import model.entity.Entity;


public class BotMyDir implements ICondition {

	BotDirection m_dir;


	public BotMyDir (BotDirection dir) {
		m_dir = dir;
	}

	@Override
	public boolean eval (Entity e) {
		return e.myDir(m_dir.getAngle(), m_dir.getAbs());
	}

}
