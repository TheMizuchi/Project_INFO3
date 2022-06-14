package controller.action;

import controller.IAction;
import model.entity.Entity;


public class BotProtect extends IAction {

	public BotProtect () {}

	@Override
	public boolean apply (Entity e) {
		throw new RuntimeException("NYI");
	}

}
