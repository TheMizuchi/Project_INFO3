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