package controller.action;

import controller.BotAction;
import model.entity.Entity;


public class BotEgg extends BotAction {

	double m_angle;


	public BotEgg (String s) {
		m_angle = s;
	}

	@Override
	public boolean apply (Entity e) {
		e.egg(Math.cos(m_angle), Math.sin(m_angle));
		return true;
	}
}
