package controller;

import model.entity.Entity;


public class RefAutomata {

	Entity m_e;
	BotAutomata m_aut;
	BotState m_current_state;
	long m_wait; // égal 0 si on attend rien


	public RefAutomata (Entity e) {
		m_wait = 0;
		m_e = e;
		Controller cont = Controller.getInstance();
		m_aut = cont.getAut(m_e.getID());
		m_current_state = m_aut.m_initial_state;
	}

	public RefAutomata (Entity e, boolean idle) {
		m_e = e;
		Controller cont = Controller.getInstance();

		if (idle) {
			m_aut = cont.getIdleAutomata();
		} else {
			m_aut = cont.getAut(m_e.m_ID);
		}
		m_current_state = m_aut.m_initial_state;
	}

	public void step () {

		if (m_wait <= 0) {
			m_current_state = m_aut.step(this);
		}
	}
}
