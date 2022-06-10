package controller;

import edu.polytech.oop.collections.IList;
import edu.polytech.oop.collections.LinkedList;


public class State {

	String m_name;
	IList m_transitions;


	public State (String name) {
		m_name = name;
		m_transitions = new LinkedList();
		m_transitions.iterator();
	}

	public State step() {
		IList.Iterator iter = m_transitions.iterator();
		State res;
		res = (State) iter.next();
		while (res == null) {
			res = (State) iter.next();
		}
		return res;
	}

	public void add_transition (Transition transi) {
		m_transitions.insertAt(m_transitions.length(), transi);
	}
}
