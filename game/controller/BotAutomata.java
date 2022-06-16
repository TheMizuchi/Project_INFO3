package controller;

import edu.polytech.oop.collections.ICollection;
import edu.polytech.oop.collections.IList;
import edu.polytech.oop.collections.LinkedList;
import model.entity.Entity;


public class BotAutomata {

	String m_name;
	BotState m_initial_state;
	IList m_states;


	public BotAutomata (String name) {
		m_name = name;
		m_states = new LinkedList();
	}

	public BotState step (Entity e, BotState current_state) {
		return current_state.step(e, this);
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

}
