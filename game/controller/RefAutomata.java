package controller;

import model.entity.Entity;


public class RefAutomata {

	Entity m_e;
	BotAutomata m_aut;
	BotState m_current_state;


	public RefAutomata (Entity e) {
		m_e = e;
		Controller cont = Controller.getInstance();
		m_aut = cont.getAut(m_e.m_ID);
		m_current_state = m_aut.m_initial_state;
	}

	public void step () {
		m_current_state = m_aut.step(m_e, m_current_state);
	}
}
