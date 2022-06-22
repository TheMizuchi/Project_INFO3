package controller.action;

import controller.BotAction;
import controller.RefAutomata;
import controller.WaitTimer;
import model.entity.Entity;


public class BotWait extends BotAction {

	long m_time;


	public BotWait (String time) {
		m_time = Long.parseLong(time);
	}

	@Override
	public boolean apply (Entity e, RefAutomata aut) {
		new WaitTimer(aut, m_time);
		return true;
	}

}
