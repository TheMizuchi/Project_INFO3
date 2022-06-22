package controller.action;

import controller.BotAction;
import controller.RefAutomata;
import model.entity.Entity;


public class BotWait extends BotAction {

	long m_time;


	public BotWait (String time) {

		if (time.equals("")) {
			m_time = 1000;
		} else {
			m_time = Long.parseLong(time);
		}
	}

	@Override
	public boolean apply (Entity e, RefAutomata aut) {
		aut.waitt(m_time);
		return true;
	}

}
