package controller.condition;

import controller.ICondition;
import model.entity.Entity;


public class BotGotPower implements ICondition {

	public BotGotPower () {}

	@Override
	public boolean eval (Entity e) {
		throw new RuntimeException("NYI");
	}

}
