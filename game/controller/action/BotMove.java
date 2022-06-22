package controller.action;

import controller.BotAction;
import controller.BotDirection;
import model.entity.Entity;
import model.entity.EntityAbsoluteDirection;
import model.entity.EntityRelativeDirection;
import model.entity.EntityType;
import model.entity.PlayerAbsoluteDirection;
import model.entity.PlayerRelativeDirection;


public class BotMove extends BotAction {

	BotDirection m_dir;


	public BotMove (BotDirection dir) {
		m_dir = dir;
	}

	@Override
	public boolean apply (Entity e) {
		EntityType type = e.getType();

		if (type == EntityType.ALLY) {

			if (m_dir.getAbs()) {
				e.move(new PlayerAbsoluteDirection(m_dir.getAngle()));
			} else {
				e.move(new PlayerRelativeDirection(e, m_dir.getAngle()));
			}
		} else {

			if (m_dir.getAbs()) {
				e.move(new EntityAbsoluteDirection(m_dir.getAngle()));
			} else {
				e.move(new EntityRelativeDirection(e, m_dir.getAngle()));
			}
		}
		return true;
	}

}
