package controller;

public class BotTransition {

	ICondition m_cond;
	IAction m_action;
	BotState m_target;

	public BotTransition(ICondition m_cond, IAction m_action, BotState m_target) {
		throw new RuntimeException("NYI");
	}

	public BotState eval () {
		throw new RuntimeException("NYI");
	}
}
