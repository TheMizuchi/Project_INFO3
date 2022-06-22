package controller.action;

import controller.BotAction;
import controller.RefAutomata;
import model.entity.Entity;


public class BotPick extends BotAction {

	public BotPick () {}

	@Override
	public boolean apply (Entity e, RefAutomata aut) {
		e.pick();
		return true;
	}

}
