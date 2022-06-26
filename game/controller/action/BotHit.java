package controller.action;

import controller.BotAction;
import controller.BotCategory;
import controller.BotDirection;
import controller.RefAutomata;
import model.entity.Entity;
import model.entity.Vector;


public class BotHit extends BotAction {

	BotDirection m_dir;
	BotCategory m_cat;


	public BotHit (String dir, String cat) {
		m_dir = new BotDirection(dir);
		m_cat = new BotCategory(cat);
	}

	@Override
	public boolean apply (Entity e, RefAutomata aut) {
		Vector vec = new Vector();
		vec.setAngle(e.getAngle());

		// Permet de cibler l'entité la plus proche de la catégorie choisie
		if (m_cat.getSel() && !m_dir.getAbs()) {
			Entity target = e.closest(m_cat.getType());

			if (target != null) {
				vec.setAngle(e.angleVers(target));
			}
		}

		double x = Math.cos(vec.getAngle());
		double y = Math.sin(vec.getAngle());
		x = Math.round(x * 1000) / 1000.0;
		y = Math.round(y * 1000) / 1000.0;
		vec.setX(x);
		vec.setY(-y);
		e.hit(vec);
		return true;
	}

}
