package controller;

import edu.polytech.oop.collections.IList;


public class BotAutomata {

	BotState m_current_state;
	BotState m_initial_state;
	IList m_states;


	public BotAutomata (BotState current_state, BotState initial_state) {
		m_current_state = current_state;
		m_initial_state = initial_state;
	}

	public BotAutomata () {}

	public void step () {
		throw new RuntimeException("NYI");
	}

}
