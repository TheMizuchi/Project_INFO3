package controller.condition;

import controller.ICondition;
import model.entity.Entity;


public class BotGotPower implements ICondition {

	public BotGotPower () {}

	@Override
	public boolean eval (Entity e) {
		return e.gotPower();
	}

}
