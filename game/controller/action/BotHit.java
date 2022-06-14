package controller.action;

import controller.IAction;
import model.entity.Entity;


public class BotHit extends IAction {

	public BotHit () {}

	@Override
	public boolean apply (Entity e) {
		throw new RuntimeException("NYI");
	}

}
