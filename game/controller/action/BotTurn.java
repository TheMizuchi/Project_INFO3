package controller.action;

import controller.BotAction;
import model.entity.Entity;


public class BotTurn extends BotAction {

	public static final double LEFT = (Math.PI)/4;
	public static final	double RIGHT = -(Math.PI)/4;
	public static final double BACK = (Math.PI)/2;
	public static final double FORWARD = 0;
	String m_s;


	public BotTurn (String s) {
		m_s = s;
	}

	@Override
	public boolean apply (Entity e) {		//quelle dir? (devant, droite ou NSEW?)

		switch (m_s) {
			case "F":
				e.rotation(FORWARD);
				break;
			case "B":
				e.rotation(BACK);
				break;
			case "L":
				e.rotation(LEFT);
				break;
			case "R":
				e.rotation(RIGHT);
				break;
		}
		return true;
	}
}
