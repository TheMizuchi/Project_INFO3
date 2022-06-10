package controller.condition;

import controller.ICondition;


public class BotTrue implements ICondition {

	@Override
	public boolean eval () {
		return true;
	}

}
