package controller.action;

import controller.IAction;
import model.entity.Entity;


public class BotThrow extends IAction {

	String m_s;


	public BotThrow (String s) {
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
