package controller.condition;

import controller.ICondition;


public class True implements ICondition {

	@Override
	public boolean eval () {
		return true;
	}

}
