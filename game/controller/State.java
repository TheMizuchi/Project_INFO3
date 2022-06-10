package controller;

import java.util.List;


public class State {

	String m_name;
	List<Transition> m_transitions;


	public State step () {
		throw new RuntimeException("NYI");
	}

	public void add_transition (Transition trans) {
		throw new RuntimeException("NYI");
	}
}
