package controller.condition;

import controller.ICondition;


public class BotCell implements ICondition {

	String m_dir;
	String m_cat;


	public BotCell (String dir, String cat) {
		m_dir = dir;
		m_cat = cat;
	}

	@Override
	public boolean eval () {
		throw new RuntimeException("NYI");
	}

}
