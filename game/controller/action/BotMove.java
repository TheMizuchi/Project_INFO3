package controller.action;

import controller.BotAction;
import model.entity.Entity;
import model.entity.EntityAbsoluteDirection;
import model.entity.EntityRelativeDirection;
import model.entity.PlayerAbsoluteDirection;
import model.entity.PlayerRelativeDirection;
import model.entity.TypeEntity;


public class BotMove extends BotAction {

	double m_angle;
	boolean m_absolute;


	public BotMove (String s) {
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
		int type = e.getType();

		if (type == TypeEntity.Allié) {

			if (m_absolute) {
				e.move(new PlayerAbsoluteDirection(m_angle));
			} else {
				e.move(new PlayerRelativeDirection(e, m_angle));
			}
		} else {

			if (m_absolute) {
				e.move(new EntityAbsoluteDirection(m_angle));
			} else {
				e.move(new EntityRelativeDirection(e, m_angle));
			}
		}
		return true;
	}

}
