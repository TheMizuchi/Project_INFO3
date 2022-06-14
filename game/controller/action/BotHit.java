package controller.action;

import controller.IAction;


public class BotHit extends IAction {

	public BotHit () {
		new BotHit("F", "_");
	}

	public BotHit (String s1) {

	}

	public BotHit (String s1, String s2) {

	}

	public boolean apply () {
		throw new RuntimeException("NYI");
	}

}
