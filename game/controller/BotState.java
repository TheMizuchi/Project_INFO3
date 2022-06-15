package controller;

import java.util.Random;

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

	public BotState step (Entity e, BotAutomata aut) {
		IList.Iterator iter = m_transitions.iterator();
		BotState state = null;

		while (iter.hasNext()) {
			state = ((BotTransition) iter.next()).eval(e);

			if (state != null) {
				break;
			}
		}

		if (state == null) {
			return this;
		} else {

			while (state.m_name.equals("_")) {
				Random ran = new Random();
				state = (BotState) aut.m_states.elementAt(ran.nextInt(aut.m_states.length()));
			}
			return state;

		}
	}

	public void add_transition (BotTransition transi) {
		m_transitions.insertAt(m_transitions.length(), transi);
	}

	public boolean equals (BotState tmp) {
		return m_name.equals(tmp.m_name);
	}

}
