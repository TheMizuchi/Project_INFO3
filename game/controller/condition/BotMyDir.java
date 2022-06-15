package controller.condition;

import controller.ICondition;


public class BotMyDir implements ICondition {

	String m_dir;


	public BotMyDir (String dir) {
		m_dir = dir;
	}

	@Override
	public boolean eval () {
		throw new RuntimeException("NYI");
	}

}
