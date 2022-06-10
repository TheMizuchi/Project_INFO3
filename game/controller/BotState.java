package controller;

import edu.polytech.oop.collections.IList;
import edu.polytech.oop.collections.LinkedList;


public class BotState {

	String m_name;
	IList m_transitions;


	public BotState (String name) {
		m_name = name;
		m_transitions = new LinkedList();
		m_transitions.iterator();
	}

	public BotState step() {
		IList.Iterator iter = m_transitions.iterator();
		BotState res;
		res = (BotState) iter.next();
		while (res == null) {
			res = (BotState) iter.next();
		}
		return res;
	}

	public void add_transition (BotTransition transi) {
		m_transitions.insertAt(m_transitions.length(), transi);
	}
}
