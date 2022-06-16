package controller.condition;

import controller.ICondition;


public class BotKey implements ICondition {

	String m_s;


	public BotKey (String s) {
		m_s = s;
	}

	@Override
	public boolean eval () {
		throw new RuntimeException("NYI");
	}

}
