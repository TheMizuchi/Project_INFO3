package controller.action;

import controller.BotAction;
import controller.RefAutomata;
import model.entity.Entity;


public class BotJump extends BotAction {

	public BotJump () {}

	@Override
	public boolean apply (Entity e, RefAutomata aut) {
		return true;
	}

}
