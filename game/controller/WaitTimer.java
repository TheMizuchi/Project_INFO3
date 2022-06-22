package controller;

import common.MyTimer;
import common.TimerListener;


public class WaitTimer implements TimerListener {

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
