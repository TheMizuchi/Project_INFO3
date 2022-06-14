package controller.condition.tree;

import controller.ICondition;

public class BotNotOp extends BotUnaryOp {

	public BotNotOp(ICondition cond) {
		m_cond = cond;
	}
	
	@Override
	public boolean eval () {
		return m_cond.eval();
	}

}
