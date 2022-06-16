package controller.action;

import controller.BotAction;
import model.entity.Direction;
import model.entity.Entity;


public class BotMove extends BotAction {

	String m_s;


	public BotMove (String s) {
		m_s = s;
	}

	@Override
	public boolean apply (Entity e) {
		return true;
	}

}
