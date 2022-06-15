package controller.action;

import controller.IAction;
import model.entity.Entity;


public class BotPop extends IAction {

	String m_s1;
	String m_s2;


	public BotPop (String s1, String s2) {
		m_s1 = s1;
		m_s2 = s2;
	}

	@Override
	public boolean apply (Entity e) {
		e.pop();
	}

}
