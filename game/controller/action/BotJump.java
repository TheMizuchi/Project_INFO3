package controller.action;

import controller.IAction;
import model.entity.Entity;


public class BotJump extends IAction {

	String m_s;


	public BotJump (String s) {
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
