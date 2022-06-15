package controller.action;

import controller.BotAction;
import model.entity.Entity;


public class BotPop extends BotAction {

	String m_s1;
	String m_s2;


	public BotPop (String s1, String s2) {
		m_s1 = s1;
		m_s2 = s2;
	}

	@Override
	public boolean apply (Entity e) {
		e.pop();
		return true;
	}

}
