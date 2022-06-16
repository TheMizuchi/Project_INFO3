package controller.action;

import controller.IAction;
import model.entity.Entity;


public class BotEgg extends IAction {

	public BotEgg () {}

	@Override
	public boolean apply (Entity e) {
		throw new RuntimeException("NYI");
	}

}
