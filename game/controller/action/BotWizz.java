package controller.action;

import controller.BotAction;
import controller.RefAutomata;
import model.entity.Entity;


public class BotWizz extends BotAction {

	public BotWizz () {}

	@Override
	public boolean apply (Entity e, RefAutomata aut) {
		e.wizz();
		return true;
	}

}
