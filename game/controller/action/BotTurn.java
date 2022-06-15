package controller.action;

import controller.IAction;
import model.entity.Entity;


public class BotTurn extends IAction {

	String m_s;


	public BotTurn (String s) {
		m_s = s;
	}

	@Override
	public boolean apply (Entity e) {		//quelle dir? (devant, droite ou NSEW?)

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
