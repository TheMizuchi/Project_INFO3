package controller.action;

import controller.BotAction;
import controller.RefAutomata;
import model.entity.Entity;


public class BotProtect extends BotAction {

	public BotProtect () {}

	@Override
	public boolean apply (Entity e, RefAutomata aut) {
		return false;
	}

}
