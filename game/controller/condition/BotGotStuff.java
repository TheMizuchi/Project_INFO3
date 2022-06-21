package controller.condition;

import controller.ICondition;
import model.entity.Entity;


public class BotGotStuff implements ICondition {

	public BotGotStuff () {}

	@Override
	public boolean eval (Entity e) {
		throw new RuntimeException("NYI");
	}

}
