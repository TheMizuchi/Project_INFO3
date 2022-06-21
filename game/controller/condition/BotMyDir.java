package controller.condition;

import controller.ICondition;
import model.entity.Entity;


public class BotMyDir implements ICondition {

	double m_angle;
	boolean m_absolute;


	public BotMyDir (String dir) {
		m_angle = 0;
		m_absolute = true;

		double ang = Math.PI / 4;

		switch (dir) {
			// Rotation relative
			case "R":
				m_angle += 2 * ang;
			case "B":
				m_angle += 2 * ang;
			case "L":
				m_angle += 2 * ang;
			case "F":
			case "":
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
	public boolean eval (Entity e) {
		return e.myDir(m_angle, m_absolute);
	}

}
