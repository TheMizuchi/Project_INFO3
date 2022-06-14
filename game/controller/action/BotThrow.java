package controller.action;

import controller.IAction;


public class BotThrow extends IAction {

	public BotThrow () {}

	public boolean apply () {
		throw new RuntimeException("NYI");
	}

}
