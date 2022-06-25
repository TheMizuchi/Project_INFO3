package controller.action;

import controller.BotAction;
import controller.RefAutomata;
import model.entity.Entity;


public class BotGet extends BotAction {

	public BotGet () {}

	@Override
	public boolean apply (Entity e, RefAutomata aut) {
		return false;
	}

}
