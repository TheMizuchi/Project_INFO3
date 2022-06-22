package controller.action;

import controller.BotAction;
import controller.RefAutomata;
import model.entity.Entity;
import controller.BotDirection;


public class BotEgg extends BotAction {

	double m_angle;


	public BotEgg (String s) {
		BotDirection dir = new BotDirection(s);
		m_angle = dir.getAngle();
	}

	@Override
	public boolean apply (Entity e, RefAutomata aut) {
		e.egg(Math.cos(m_angle), Math.sin(m_angle));
		return true;
	}
}
