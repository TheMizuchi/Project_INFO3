package controller.action;

import controller.BotAction;
import model.entity.Entity;
import model.entity.EntityInterface;
import model.entity.EntityType;


public class BotTurn extends BotAction {

	double m_angle;
	boolean m_absolute;
	boolean m_select_cible;


	public BotTurn (String s) {
		m_angle = 0;
		m_absolute = true;
		m_select_cible = false;

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

	public BotTurn () {

		m_angle = 0;
		m_absolute = true;
		m_select_cible = true;
	}

	@Override
	public boolean apply (Entity e) {

		// ne cible que les joueurs, fonction réservée aux monstres (ou aux margoulin qui veulent voler des torches)
		if (m_select_cible) {
			Entity cible = e.closest(EntityType.ALLY);
			m_angle = e.angleVers(cible);
		}

		e.turn(m_angle, m_absolute);
		return true;

	}
}
