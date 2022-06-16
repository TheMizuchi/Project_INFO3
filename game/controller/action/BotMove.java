package controller.action;

import controller.BotAction;
import model.entity.Direction;
import model.entity.Entity;


public class BotMove extends BotAction {

	String m_s;


	public BotMove (String s) {
		m_s = s;
	}

	@Override
	public boolean apply (Entity e) {

		Direction dir = new Direction();

		switch (m_s) {
			case "N":
				dir.faceNorth();
				e.move(dir);
				break;
			case "W":
				dir.faceWest();
				e.move(dir);
				break;
			case "S":
				dir.faceSouth();
				e.move(dir);
				break;
			case "E":
				dir.faceEst();
				e.move(dir);
				break;
			case "NE":
				dir.faceNorth();
				dir.faceEst();
				e.move(dir);
				break;
			case "NW":
				dir.faceNorth();
				dir.faceWest();
				e.move(dir);
				break;
			case "SE":
				dir.faceSouth();
				dir.faceEst();
				e.move(dir);
				break;
			case "SW":
				dir.faceSouth();
				dir.faceWest();
				e.move(dir);
				break;
		}
		return true;
	}

}
