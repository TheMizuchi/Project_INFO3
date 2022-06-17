package controller.action;

import controller.BotAction;
import model.entity.Entity;


public class BotTurn extends BotAction {

	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int BACK = 2;
	public static final int FORWARD = 3;
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
