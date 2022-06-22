package controller;

import common.MyTimer;
import common.TimerListener;
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

	public void waitt (long time) {
		m_wait = time;
		new WaitTimer(this, m_wait);
	}


	private class WaitTimer implements TimerListener {

		RefAutomata m_aut;
		long m_last;


		public WaitTimer (RefAutomata aut, long time) {
			m_aut = aut;
			MyTimer mt = MyTimer.getTimer();
			m_last = System.currentTimeMillis();
			mt.setTimer(5, this);
		}

		@Override
		public void expired () {
			long time = System.currentTimeMillis();
			m_aut.m_wait -= time - m_last;

			if (m_aut.m_wait <= 0) {
				m_aut.m_wait = 0;
			} else {
				MyTimer mt = MyTimer.getTimer();
				mt.setTimer(5, this);
				m_last = time;
			}
		}

	}
}
