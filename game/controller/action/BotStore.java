package controller.action;

import controller.BotAction;
import controller.RefAutomata;
import model.entity.Entity;


public class BotStore extends BotAction {

	public BotStore () {}

	@Override
	public boolean apply (Entity e, RefAutomata aut) {
		e.store();
		return true;
	}
}
