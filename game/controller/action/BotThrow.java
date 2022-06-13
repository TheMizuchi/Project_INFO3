package controller.action;

import controller.IAction;


public class BotThrow implements IAction {


	public BotThrow () {
	}

	@Override
	public boolean apply () {
		throw new RuntimeException("NYI");
	}

}
