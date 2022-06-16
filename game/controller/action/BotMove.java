package controller.action;

import controller.BotAction;
import controller.Controller;
import model.entity.Direction;
import model.entity.Entity;
import model.entity.PlayerAbsoluteDirection;


public class BotMove extends BotAction {

	String m_s;


	public BotMove (String s) {
		m_s = s;
	}

	@Override
	public boolean apply (Entity e) {
		double angle = 0;

		switch (m_s) {
			case "N":
				angle = Math.PI / 2;
				break;
			case "S":
				angle = 3 * Math.PI / 2;
				break;
			case "W":
				angle = Math.PI;
				break;
			case "E":
				angle = 0;
				break;
			default:
				break;
		}
		Direction dir = new PlayerAbsoluteDirection(angle);
		e.move(dir);
		return true;
	}

}
