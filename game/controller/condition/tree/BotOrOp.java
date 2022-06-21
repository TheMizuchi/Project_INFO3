package controller.condition.tree;

import controller.ICondition;
import model.entity.Entity;


public class BotOrOp extends BotBinaryOp {

	public BotOrOp (ICondition l, ICondition r) {
		m_left = l;
		m_right = r;
	}

	@Override
	public boolean eval (Entity e) {
		return m_left.eval(e) || m_right.eval(e);
	}

}
