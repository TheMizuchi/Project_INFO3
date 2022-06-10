package controller;

import controller.action.BotMove;
import controller.condition.BotTrue;


public class BotTestAutomate {

	public static void main (String args[]) {
		BotState init = new BotState("init");
		BotState etat2 = new BotState("etat2");
		BotAutomata B2 = new BotAutomata(init, init);
		B2.add_state(etat2);

		BotTransition transi = new BotTransition(new BotTrue(), new BotMove(), etat2);
		init.add_transition(transi);
		BotTransition transi2 = new BotTransition(new BotTrue(), new BotMove(), etat2);
		init.add_transition(transi2);
		BotTransition transi3 = new BotTransition(new BotTrue(), new BotMove(), etat2);
		init.add_transition(transi3);
		BotTransition transi4 = new BotTransition(new BotTrue(), new BotMove(), etat2);
		init.add_transition(transi4);
		BotTransition transi5 = new BotTransition(new BotTrue(), new BotMove(), init);
		etat2.add_transition(transi5);

		while (true) {
			B2.step();
		}

		//System.out.println("zer");
	}

}
