package controller;

public class Transition {

	ICondition m_cond;
	IAction m_action;
	State m_target;


	public Transition (ICondition cond, IAction action,	State target) {
		m_cond = cond;
		m_action = action;
		m_target = target;
	}

	public State eval () {

		if (m_cond.eval()) {
			m_action.apply();
			return m_target;
		}
		return null;
	}

}
