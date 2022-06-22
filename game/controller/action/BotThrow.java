package controller.action;

import controller.BotAction;
import controller.BotDirection;
import model.entity.Entity;


public class BotThrow extends BotAction {

	BotDirection m_dir;


	public BotThrow (BotDirection dir) {
		m_dir = dir;
	}

	@Override
	public boolean apply (Entity e) {
		return true;
	}

}
