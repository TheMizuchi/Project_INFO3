package controller.condition;

import controller.ICondition;


public class BotTrue implements ICondition {

	public BotTrue () {

	}

	@Override
	public boolean eval () {
		return true;
	}

}
