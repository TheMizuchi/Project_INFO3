package controller.action;

import controller.BotAction;
import model.entity.Entity;


public class BotWait extends BotAction {

	long m_time;


	public BotWait (long time) {
		m_time = time;
	}

	@Override
	public boolean apply (Entity e) {
		// IN WORK
		return true;
	}

	//	private class WaitTimer implements TimerListener {
	//
	//		Entity m_e;
	//		long m_init;
	//
	//
	//		WaitTimer (Entity p, long time) {
	//			m_e = e;
	//			MyTimer mt = MyTimer.getTimer();
	//			m_init = System.currentTimeMillis();
	//			mt.setTimer(5, this);
	//		}
	//
	//		@Override
	//		public void expired () {
	//			long time = System.currentTimeMillis();
	//			m_p.m_possessionCD -= time - m_init;
	//
	//			if (m_p.m_possessionCD <= 0) {
	//				m_p.m_possessionCD = 0;
	//			} else {
	//				MyTimer mt = MyTimer.getTimer();
	//				mt.setTimer(5, this);
	//				m_init = time;
	//			}
	//		}
	//
	//	}
}
