package controller.action;

import controller.BotAction;
import model.entity.Entity;


public class BotNone extends BotAction {

	public BotNone () {}

	@Override
	public boolean apply (Entity e) {
		throw new RuntimeException("NYI");
	}

}
