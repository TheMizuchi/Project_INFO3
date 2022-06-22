package controller.condition;

import controller.ICondition;
import model.entity.Entity;


public class BotTrue implements ICondition {

	public BotTrue () {}

	@Override
	public boolean eval (Entity e) {
		return true;
	}

}
