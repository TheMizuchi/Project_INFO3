package controller.condition.tree;

import controller.ICondition;

public class BotOrOp extends BotBinaryOp {

	public BotOrOp(ICondition l, ICondition r) {
		m_left = l;
		m_right = r;
	}
	
	@Override
	public boolean eval () {
		return m_left.eval() || m_right.eval();
	}

}
