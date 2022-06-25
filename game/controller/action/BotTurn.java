package controller.action;

import controller.BotAction;
import controller.BotCategory;
import controller.BotDirection;
import controller.RefAutomata;
import model.entity.Entity;


public class BotTurn extends BotAction {

	BotDirection m_dir;
	BotCategory m_cat;


	public BotTurn (String dir, String cat) {
		m_dir = new BotDirection(dir);
		m_cat = new BotCategory(cat);
	}

	@Override
	public boolean apply (Entity e, RefAutomata aut) {

		// Permet de cibler l'entité la plus proche de la catégorie choisie
		if (m_cat.getSel() && !m_dir.getAbs()) {
			Entity target = e.closest(m_cat.getType());
			e.turn(m_dir.getAngle() + e.angleVers(target), true);
		} else {
			e.turn(m_dir.getAngle(), m_dir.getAbs());
		}
		return true;

	}
}
