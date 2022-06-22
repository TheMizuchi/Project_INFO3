package controller.action;

import controller.BotAction;
import model.entity.Entity;


public class BotPop extends BotAction {

	public BotPop () {}

	@Override
	public boolean apply (Entity e) {
		e.pop();
		return true;
	}

}
