package controller.condition;

import controller.ICondition;


public class BotKey implements ICondition {

	char m_c;
	
	public BotKey(char c) {
		m_c = c;
	}
	
	@Override
	public boolean eval () {
		throw new RuntimeException("NYI");
	}

}
