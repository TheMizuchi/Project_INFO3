package controller.action;

import controller.BotAction;
import controller.RefAutomata;
import model.entity.Entity;


public class BotExplode extends BotAction {

	public BotExplode () {}

	@Override
	public boolean apply (Entity e, RefAutomata aut) {
		return false;
	}

}
