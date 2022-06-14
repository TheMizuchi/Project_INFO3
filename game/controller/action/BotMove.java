package controller.action;

import controller.IAction;


public class BotMove extends IAction {

	String m_s;


	public BotMove (String s) {
		m_s = s;
	}

	public boolean apply () {
		throw new RuntimeException("NYI");
	}

}
