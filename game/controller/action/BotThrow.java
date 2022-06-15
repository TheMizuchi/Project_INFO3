package controller.action;

import controller.BotAction;
import model.entity.Entity;


public class BotThrow extends BotAction {

	public BotThrow () {}

	@Override
	public boolean apply (Entity e) {
		throw new RuntimeException("NYI");
	}

}
