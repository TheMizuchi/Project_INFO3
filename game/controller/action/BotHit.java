package controller.action;

import controller.BotAction;
import controller.BotDirection;
import controller.RefAutomata;
import model.entity.Entity;
import model.entity.Vector;


public class BotHit extends BotAction {

	BotDirection m_dir;


	public BotHit (String dir) {
		m_dir = new BotDirection(dir);
	}

	@Override
	public boolean apply (Entity e, RefAutomata aut) {
		Vector vec = new Vector();
		vec.setAngle(e.getAngle());
		double x = Math.cos(m_dir.getAngle());
		double y = Math.sin(m_dir.getAngle());
		x = Math.round(x);
		y = Math.round(y);
		vec.setX(x);
		vec.setY(-y);
		e.hit(vec);
		return true;
	}

}
