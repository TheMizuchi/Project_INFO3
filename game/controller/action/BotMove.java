package controller.action;

import controller.BotAction;
import controller.Controller;
import model.entity.Direction;
import model.entity.Entity;
import model.entity.PlayerAbsoluteDirection;
import model.entity.PlayerRelativeDirection;


public class BotMove extends BotAction {

	String m_s;


	public BotMove (String s) {
		m_s = s;
	}

	@Override
	public boolean apply (Entity e) {

		switch (m_s) {
			case "N":
				e.move(new PlayerAbsoluteDirection(Math.PI / 2));
				break;
			case "W":
				e.move(new PlayerAbsoluteDirection(Math.PI));
				break;
			case "S":
				e.move(new PlayerAbsoluteDirection(3 * Math.PI / 2));
				break;
			case "E":
				e.move(new PlayerAbsoluteDirection(0));
				break;
			case "F":
				e.move(new PlayerRelativeDirection(e, 0));
				break;
			case "L":
				e.move(new PlayerRelativeDirection(e, Math.PI / 2));
				break;
			case "B":
				e.move(new PlayerRelativeDirection(e, Math.PI));
				break;
			case "R":
				e.move(new PlayerRelativeDirection(e, 3 * Math.PI / 2));
				break;
			default:
				break;
		}
		return true;
	}

}
