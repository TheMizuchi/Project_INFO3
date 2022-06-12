package controller.action;

import controller.IAction;


public class BotNone implements IAction {

	@Override
	public boolean apply () {
		throw new RuntimeException("NYI");
	}

}
