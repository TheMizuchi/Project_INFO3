package controller.action;

import controller.IAction;
import model.entity.Entity;


public class BotThrow extends IAction {

	public BotThrow () {}

	@Override
	public boolean apply (Entity e) {
		throw new RuntimeException("NYI");
	}

}
