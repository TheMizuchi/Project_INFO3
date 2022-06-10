package controller;

public class BotAutomata {

	State m_current_state;
	State m_initial_state;
	


	public void step () {
		m_current_state = m_current_state.step();
	}
	
}
