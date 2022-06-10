package controller;

public class Transition {

	ICondition m_cond;
	IAction m_action;
	State m_target;

	public Transition(ICondition m_cond, IAction m_action, State m_target) {
		throw new RuntimeException("NYI");
	}

	public State eval () {
		throw new RuntimeException("NYI");
	}
}
