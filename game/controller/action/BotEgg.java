package controller.action;

import controller.BotAction;
import controller.RefAutomata;
import model.entity.Entity;


public class BotEgg extends BotAction {

	String m_s;


	public BotEgg (String s) {
		m_s = s;
	}

	@Override
	public boolean apply (Entity e, RefAutomata aut) {
		return true;
	}

}
