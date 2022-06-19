package controller.action;

import controller.BotAction;
import model.entity.Entity;


public class BotPick extends BotAction {

	String m_s;


	public BotPick (String s) {
		m_s = s;
	}

	@Override
	public boolean apply (Entity e) {
		e.pick();

		switch (m_s) {
			case "F":

				break;
			case "B":

				break;
			case "L":

				break;
			case "R":

				break;
		}
		return true;
	}

}
