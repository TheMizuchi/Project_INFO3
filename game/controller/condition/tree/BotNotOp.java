package controller.condition.tree;

import controller.ICondition;
import model.entity.Entity;


public class BotNotOp extends BotUnaryOp {

	public BotNotOp (ICondition cond) {
		m_cond = cond;
	}

	@Override
	public boolean eval (Entity e) {
		return !m_cond.eval(e);
	}

}
