package controller;

public class BotTransition {

	ICondition m_cond;
	IAction m_action;
	BotState m_target;

	public Transition(ICondition m_cond, IAction m_action, State m_target) {
		throw new RuntimeException("NYI");
	}

	public BotState eval () {
		throw new RuntimeException("NYI");
	}
}
