package controller.action;

import controller.IAction;
import model.entity.Entity;


public class BotNone extends IAction {

	public BotNone () {}

	@Override
	public boolean apply (Entity e) {
		throw new RuntimeException("NYI");
	}

}
