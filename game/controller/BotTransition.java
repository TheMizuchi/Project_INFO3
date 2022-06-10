package controller;

public class BotTransition {

	ICondition m_cond;
	IAction m_action;
	BotState m_target;


	public BotTransition (ICondition cond, IAction action, BotState target) {
		m_cond = cond;
		m_action = action;
		m_target = target;
	}

	public BotState eval () {

		if (m_cond.eval()) {
			m_action.apply();
			return m_target;
		}
		return null;
	}

}
