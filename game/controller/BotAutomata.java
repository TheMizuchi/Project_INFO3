package controller;

public class BotAutomata {


	BotState m_current_state;
	BotState m_initial_state;

	public BotAutomata(BotState current_state, BotState initial_state) {
		m_current_state = current_state;
		m_initial_state = initial_state;
	}

	public void step () {
		m_current_state = m_current_state.step();
	}
	
}
