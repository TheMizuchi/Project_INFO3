package controller.condition.tree;

import controller.ICondition;
import model.entity.Entity;


public class BotAndOp extends BotBinaryOp {

	public BotAndOp (ICondition l, ICondition r) {
		m_left = l;
		m_right = r;
	}

	@Override
	public boolean eval (Entity e) {
		return m_left.eval(e) && m_right.eval(e);
	}

}
