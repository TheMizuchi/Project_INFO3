package controller.condition;

import controller.Controller;
import controller.ICondition;


public class BotKey implements ICondition {

	char m_c;


	public BotKey (String s) {
		m_c = s.toUpperCase().charAt(0);

		switch (s) {
			case "FU":
				m_c = 38;
				break;
			case "FL":
				m_c = 37;
				break;
			case "FD":
				m_c = 40;
				break;
			case "FR":
				m_c = 39;
				break;
			case "SPACE":
				m_c = 32;
				break;
			default:
				break;
		}
	}

	@Override
	public boolean eval () {
		Controller surveillant = Controller.getInstance();

		if (surveillant.isdir(m_c) && (surveillant.getKey(m_c) ^ surveillant.getKeyPrev(m_c))) {
			surveillant.useKey(m_c);
			return true;
		} else if (!surveillant.isdir(m_c) && !surveillant.getKeyPrev(m_c) && surveillant.getKey(m_c)) {
			surveillant.useKey(m_c);
			return true;
		} else if (surveillant.getKey(m_c) ^ surveillant.getKeyPrev(m_c)) {
			surveillant.useKey(m_c);
			return false;
		}
		return false;
	}

}
