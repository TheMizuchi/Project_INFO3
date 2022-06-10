package controller.action;

import controller.IAction;


public class BotMove implements IAction {

	String m_s;


	public BotMove (String s) {
		m_s = s;
	}

	@Override
	public boolean apply () {
		return true;
	}

}
