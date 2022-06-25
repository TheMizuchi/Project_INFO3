package controller.action;

import controller.BotAction;
import controller.BotCategory;
import controller.BotDirection;
import controller.RefAutomata;
import model.entity.Entity;
import model.entity.EntityAbsoluteDirection;
import model.entity.EntityRelativeDirection;
import model.entity.EntityType;
import model.entity.PlayerAbsoluteDirection;
import model.entity.PlayerRelativeDirection;


public class BotMove extends BotAction {

	BotDirection m_dir;
	BotCategory m_cat;


	public BotMove (String dir, String cat) {
		m_dir = new BotDirection(dir);
		m_cat = new BotCategory(cat);
	}

	@Override
	public boolean apply (Entity e, RefAutomata aut) {
		EntityType type = e.getType();

		double angle = m_dir.getAngle();

		// Permet de cibler l'entité la plus proche de la catégorie choisie
		if (m_cat.getSel() && !m_dir.getAbs()) {
			Entity target = e.closest(m_cat.getType());

			if (target != null) {
				angle += e.angleVers(target);
			}
		}

		if (type == EntityType.ALLY) {

			if (!m_dir.getAbs() && !m_cat.getSel()) {
				e.move(new PlayerRelativeDirection(e, angle));
			} else {
				e.move(new PlayerAbsoluteDirection(angle));
			}
		} else {

			if (!m_dir.getAbs() && !m_cat.getSel()) {
				e.move(new EntityRelativeDirection(e, angle));
			} else {
				e.move(new EntityAbsoluteDirection(angle));
			}
		}
		return true;
	}

}
