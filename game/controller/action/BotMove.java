package controller.action;

import controller.IAction;
import model.entity.Entity;


public class BotMove extends IAction {

	String m_s;


	public BotMove (String s) {
		m_s = s;
	}

	@Override
	public boolean apply (Entity e) {

		switch (m_s) {
			case "N":
				e.move(0, -1);
				break;
			case "W":
				e.move(-1, 0);
				break;
			case "S":
				e.move(0, 1);
				break;
			case "E":
				e.move(1, 0);
				break;
		}
		return false;
	}

}
