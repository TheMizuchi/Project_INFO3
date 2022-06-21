package controller.action;

import controller.BotAction;
import controller.BotDirection;
import model.entity.Entity;


public class BotHit extends BotAction {

	BotDirection m_dir;


	public BotHit (BotDirection dir) {
		m_dir = dir;
	}

	@Override
	public boolean apply (Entity e) {
		// Si quelqu'un à un conflit (notamment Maxime) c'est Diego qui a modifié le constructeur, vient me voir voc
		return true;
	}

}
