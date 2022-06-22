package controller.action;

import controller.BotAction;
import controller.BotDirection;
import controller.RefAutomata;
import model.entity.Entity;
import model.entity.EntityType;


public class BotTurn extends BotAction {

	BotDirection m_dir;


	public BotTurn (String dir) {
		m_dir = new BotDirection(dir);
	}

	@Override
	public boolean apply (Entity e, RefAutomata aut) {

		// ne cible que les joueurs, fonction réservée aux monstres (ou aux margoulin qui veulent voler des torches)
		if (m_dir.getSel()) {
			Entity cible = e.closest(EntityType.ALLY);
			m_dir.setAngle(e.angleVers(cible));
		}

		e.turn(m_dir.getAngle(), m_dir.getAbs());
		return true;

	}
}
