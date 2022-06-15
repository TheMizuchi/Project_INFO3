package controller.action;

import controller.BotAction;
import model.entity.Entity;


public class BotEgg extends BotAction {

	public BotEgg () {}

	@Override
	public boolean apply (Entity e) {
		throw new RuntimeException("NYI");
	}

}
