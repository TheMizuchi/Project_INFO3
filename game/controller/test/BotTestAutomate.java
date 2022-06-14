package controller.test;

import controller.BotAutomata;
import controller.BotState;
import controller.BotTransition;
import controller.action.BotMove;
import controller.condition.BotTrue;
import model.entity.Entity;


public class BotTestAutomate {

	public static void main (String args[]) {
		BotState init = new BotState("init");
		BotAutomata B2 = new BotAutomata("pouet");
		B2.add_state("etat2");

		Entity e = new Entity(0, 0, 0);

		/*
		 * BotTransition transi = new BotTransition(new BotTrue(), new BotMove("N"),
		 * etat2); init.add_transition(transi); BotTransition transi2 = new
		 * BotTransition(new BotTrue(), new BotMove("N"), etat2);
		 * init.add_transition(transi2); BotTransition transi3 = new BotTransition(new
		 * BotTrue(), new BotMove("N"), etat2); init.add_transition(transi3);
		 * BotTransition transi4 = new BotTransition(new BotTrue(), new BotMove("N"),
		 * etat2); init.add_transition(transi4); BotTransition transi5 = new
		 * BotTransition(new BotTrue(), new BotMove("N"), init);
		 * etat2.add_transition(transi5);
		 */
		while (true) {
			e.update();
		}

		//System.out.println("zer");
	}

}
