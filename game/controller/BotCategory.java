package controller;

import model.entity.EntityType;


public class BotCategory {

	EntityType m_type;
	boolean m_select_cible;


	public BotCategory (String s) {

		if (s.equals("")) {
			m_select_cible = false;
		} else {
			m_select_cible = true;
		}

		switch (s) {
			case "T": // Team
			case "@": // Player
			case "":
				m_type = EntityType.ALLY;
				break;

			case "A": // Ennemy
			case "D": // Danger
			case "M": // Missile
				m_type = EntityType.ENEMY;
				break;

			case "V": // Void
				m_type = EntityType.NEUTRAL;
				break;

			case "O": // Obstacle
				m_type = EntityType.OBSTACLE;
				break;

			// Inutile dans le jeu :
			case "P": // Pickable
				m_type = EntityType.ITEM;
				break;
			case "_": // All except void
			case "C": // Clue
			case "G": // Gate
			case "J": // Jumpable
			default:
				m_type = EntityType.NEUTRAL;
				break;
		}
	}

	public EntityType getType () {
		return m_type;
	}

	public boolean getSel () {
		return m_select_cible;
	}

}
