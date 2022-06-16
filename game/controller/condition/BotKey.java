package controller.condition;

import controller.Controller;
import controller.ICondition;


public class BotKey implements ICondition {

	String m_s;


	public BotKey (String s) {
		m_s = s;
	}

	@Override
	public boolean eval () {
		Controller surveillant = Controller.getInstance();
		m_s = m_s.toUpperCase();

		if (surveillant.getTabKeys_prev()[m_s.codePointAt(0) - 65]) {
			System.out.println(m_s);
			return true;
		}
		return false;
	}

}
