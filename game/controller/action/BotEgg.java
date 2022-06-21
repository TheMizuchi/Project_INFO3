package controller.action;

import controller.BotAction;
import model.entity.Entity;


public class BotEgg extends BotAction {

	String m_s;


	public BotEgg (String s) {
		m_s = s;
	}

	@Override
	public boolean apply (Entity e) {

		switch (m_s) {
			case "S":
				e.egg(0, 1);
				break;
			case "W":
				e.egg(-1, 0);
				break;
			case "N":
				e.egg(0, -1);
				break;
			case "E":
				e.egg(1, 0);
				break;
		}
		return true;
	}
}
