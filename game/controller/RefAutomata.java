package controller;


public class RefAutomata {
	Entity m_e;
	BotAutomata m_aut;
	BotState m_current_state;
	
	public RefAutomata(Entity e, BotAutomata aut) {
		m_e = e;
		Controller cont = Controller.getController();
		m_aut = cont.auts[m_e.getId()];
		m_current_state = aut.m_initial_state;
	}
	
	public boolean step() {
		m_aut.step(m_e);
	}
}
