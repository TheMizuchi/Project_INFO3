package controller.action;

import controller.BotAction;
import controller.RefAutomata;
import model.entity.Entity;


public class BotPop extends BotAction {

	public BotPop () {}

	@Override
	public boolean apply (Entity e, RefAutomata aut) {
		e.pop();
		return true;
	}

}
