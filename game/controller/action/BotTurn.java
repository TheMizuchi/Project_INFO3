package controller.action;

import controller.BotAction;
import model.entity.Entity;


public class BotTurn extends BotAction {

	private static final double LEFT = Math.PI / 2;
	private static final double RIGHT = 3 * Math.PI / 2;
	private static final double BACK = Math.PI;
	private static final double FORWARD = 0;
	String m_s;


	public BotTurn (String s) {
		m_s = s;
	}

	@Override
	public boolean apply (Entity e) {

		switch (m_s) {
			// Rotation absolue
			case "N":
				e.turn(Math.PI / 2, true);
				break;
			case "W":
				e.turn(Math.PI, true);
				break;
			case "S":
				e.turn(3 * Math.PI / 2, true);
				break;
			case "E":
				e.turn(0, true);
				break;
			case "NW":
				e.turn(3 * Math.PI / 4, true);
				break;
			case "NE":
				e.turn(Math.PI / 4, true);
				break;
			case "SE":
				e.turn(7 * Math.PI / 4, true);
				break;
			case "SW":
				e.turn(5 * Math.PI / 4, true);
				break;

			// Rotation relative
			case "F":
				e.turn(FORWARD, false);
				break;
			case "B":
				e.turn(BACK, false);
				break;
			case "L":
				e.turn(LEFT, false);
				break;
			case "R":
				e.turn(RIGHT, false);
				break;
		}
		return true;
	}
}
