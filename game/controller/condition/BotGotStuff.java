package controller.condition;

import controller.ICondition;


public class BotGotStuff implements ICondition {

	public BotGotStuff () {}

	@Override
	public boolean eval () {
		throw new RuntimeException("NYI");
	}

}
