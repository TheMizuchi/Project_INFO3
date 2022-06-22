package controller.action;

import controller.BotAction;
import controller.BotDirection;
import controller.RefAutomata;
import model.entity.Entity;


public class BotHit extends BotAction {

	BotDirection m_dir;


	public BotHit (String dir) {
		m_dir = new BotDirection(dir);
	}

	@Override
	public boolean apply (Entity e, RefAutomata aut) {
		// Si quelqu'un à un conflit (notamment Maxime) c'est Diego qui a modifié le constructeur, vient me voir voc
		return true;
	}

}
