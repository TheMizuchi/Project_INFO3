package controller.action;

import controller.BotAction;
import controller.BotDirection;
import controller.RefAutomata;
import model.entity.Entity;


public class BotThrow extends BotAction {

	BotDirection m_dir;


	public BotThrow (String dir) {
		m_dir = new BotDirection(dir);
	}

	@Override
	public boolean apply (Entity e, RefAutomata aut) {
		return true;
	}

}
