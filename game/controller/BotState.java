package controller;

import edu.polytech.oop.collections.IList;
import edu.polytech.oop.collections.LinkedList;
import model.entity.Entity;


public class BotState {

	String m_name;
	IList m_transitions;


	public BotState (String name) {
		m_name = name;
		m_transitions = new LinkedList();
		m_transitions.iterator();
	}

	public BotState step (Entity e) {
		IList.Iterator iter = m_transitions.iterator();
		BotTransition transi;
		BotState res;
		transi = (BotTransition) iter.next();
		res = transi.eval(e);

		while (res == null) {
			transi = (BotTransition) iter.next();
			res = transi.eval(e);
		}
		return res;
	}

	public void add_transition (BotTransition transi) {
		m_transitions.insertAt(m_transitions.length(), transi);
	}

	public boolean equals (BotState tmp) {
		return m_name.equals(tmp.m_name);
	}

}
