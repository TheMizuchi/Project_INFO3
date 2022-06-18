package controller.action;

import controller.BotAction;
import model.entity.Entity;


public class BotTurn extends BotAction {

	double m_angle;
	boolean m_absolute;


	public BotTurn (String s) {
		m_angle = 0;
		m_absolute = true;

		double ang = Math.PI / 4;

		switch (s) {
			// Rotation relative
			case "R":
				m_angle += 2 * ang;
			case "B":
				m_angle += 2 * ang;
			case "L":
				m_angle += 2 * ang;
			case "F":
				m_absolute = false;
				break;

			// Rotation absolue
			case "SE":
				m_angle += ang;
			case "S":
				m_angle += ang;
			case "SW":
				m_angle += ang;
			case "W":
				m_angle += ang;
			case "NW":
				m_angle += ang;
			case "N":
				m_angle += ang;
			case "NE":
				m_angle += ang;
			case "E":
				break;
		}
	}

	@Override
	public boolean apply (Entity e) {
		e.turn(m_angle, m_absolute);
		return true;
	}
}
