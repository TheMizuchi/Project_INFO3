package common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import edu.polytech.oop.collections.LinkedList;


public class MyTimer {

	Timer m_t;
	private static MyTimer self;
	LinkedList m_timerList;
	LinkedList m_delayList;


	public MyTimer () {
		self = this;
		m_t = new Timer(0, new FireTimerEvent());
		m_t.setRepeats(false);
		m_t.start();
		m_timerList = new LinkedList();
		m_delayList = new LinkedList();
	}

	public static MyTimer getTimer () {
		return self;
	}

	/**
	 * This method sets a timer to the given delay in milliseconds. It cancels any
	 * pending timer, if there was any. Use a delay=-1 to just cancel an existing
	 * timer without setting a new one.
	 * 
	 * @param delay
	 * @param listener
	 */
	public void setTimer (int delay, TimerListener listener) {
		m_t.stop();

		int i = 0;

		while (i < m_timerList.length()) {						//On supprime le delay associé au timer si ce dernier en possède déjà un 

			if ((TimerListener) m_timerList.elementAt(i) == listener) {
				m_timerList.removeAt(i);						//On effectue toujours les opération simultanément sur les deux liste afin de les maintenir synchronisé
				m_delayList.removeAt(i);
				break;
			}
			i++;
		}

		if (delay >= 0) {										//Si delay est valide, on ajoute ce timer au bon endroit dans la liste
			i = 0;
			int d;

			if (m_delayList.length() > 0) {
				d = (int) m_delayList.elementAt(i);

				while (delay >= d && i < m_delayList.length()) {
					delay -= d;
					i++;
					if (i < m_delayList.length())
						d = (int) m_delayList.elementAt(i);
				}
			}
			m_delayList.insertAt(i, delay);
			m_timerList.insertAt(i, listener);
			i++;

			//while (i < m_delayList.length()) {
			if (i < m_delayList.length())
				m_delayList.updateAt(i, (int) m_delayList.elementAt(i) - delay); //On update les timers sans changer ni le nombre ni l'ordre donc pas besoin de modier m_timerList
			//i++;
			//}
		}
		this.startNextTimer();
	}

	void startNextTimer () {

		if (m_timerList.length() > 0) {
			m_tl = (TimerListener) m_timerList.elementAt(0);
			m_t.setInitialDelay((int) m_delayList.elementAt(0));

			if (((int) m_delayList.elementAt(0)) == 0) {
				expired();
			}
			m_t.start();
		} else {
			m_tl = null;
		}
	}

	void expired () {

		if (m_tl != null) {
			m_timerList.removeAt(0);
			m_delayList.removeAt(0);
			TimerListener tl = m_tl;
			startNextTimer();
			tl.expired();
		}
	}


	TimerListener m_tl;


	class FireTimerEvent implements ActionListener {

		@Override
		public void actionPerformed (ActionEvent e) {
			expired();
		}

	}

}
