package controller.action;

import controller.BotAction;
import model.entity.Entity;


public class BotProtect extends BotAction {

	public BotProtect () {}

	@Override
	public boolean apply (Entity e) {
		throw new RuntimeException("NYI");
	}

}
