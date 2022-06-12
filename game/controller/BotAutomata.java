package controller;

import edu.polytech.oop.collections.ICollection;
import edu.polytech.oop.collections.IList;
import edu.polytech.oop.collections.LinkedList;


public class BotAutomata {

	String m_name;
	BotState m_current_state;
	BotState m_initial_state;
	IList m_states;


	public BotAutomata (String name) {
		m_name = name;
		m_states = new LinkedList();
	}

	public BotAutomata (BotState current_state, BotState initial_state) {
		m_current_state = current_state;
		m_initial_state = initial_state;
		m_states = new LinkedList();
		add_state(initial_state);
		add_state(current_state);
	}

	public BotAutomata () {
		m_states = new LinkedList();
	}

	public void step () {
		m_current_state = m_current_state.step();
	}

	public BotState add_state (String state_name) {
		ICollection.Iterator iter = m_states.iterator();
		BotState tmp;

		while (iter.hasNext()) {
			tmp = (BotState) iter.next();

			if (tmp.m_name.equals(state_name)) {
				return tmp;
			}

		}
		BotState state = new BotState(state_name);
		m_states.insertAt(m_states.length(), state);
		return state;
	}

	public boolean add_state (BotState state) {
		ICollection.Iterator iter = m_states.iterator();
		BotState tmp;

		while (iter.hasNext()) {
			tmp = (BotState) iter.next();

			if (state.equals(tmp)) {
				return false;
			}

		}
		m_states.insertAt(m_states.length(), state);
		return true;
	}

	public void add_transition (String state_name, BotTransition transition) {
		ICollection.Iterator iter = m_states.iterator();
		BotState state = (BotState) iter.next();
		while (state.m_name != state_name)
			state = (BotState) iter.next();
		state.add_transition(transition);
	}

	public void add_transition (BotState state, BotTransition transition) {
		state.add_transition(transition);
	}

}
