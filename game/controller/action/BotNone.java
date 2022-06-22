package controller.action;

import controller.BotAction;
import controller.RefAutomata;
import model.entity.Entity;


public class BotNone extends BotAction {

	public BotNone () {}

	@Override
	public boolean apply (Entity e, RefAutomata aut) {
		return true;
	}

}
