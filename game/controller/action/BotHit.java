package controller.action;

import controller.BotAction;
import model.entity.Entity;


public class BotHit extends BotAction {

	String m_s;


	public BotHit (String s) {
		m_s = s;
	}

	@Override
	public boolean apply (Entity e) {

		switch (m_s) {
			case "N":

				break;
			case "W":

				break;
			case "S":

				break;
			case "E":

				break;
		}
		return true;
	}

}
