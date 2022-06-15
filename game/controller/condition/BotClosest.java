package controller.condition;

import controller.ICondition;


public class BotClosest implements ICondition {

	String m_dir;
	String m_cat;


	public BotClosest (String dir, String cat) {
		m_dir = dir;
		m_cat = cat;
	}

	@Override
	public boolean eval () {
		throw new RuntimeException("NYI");
	}

}
