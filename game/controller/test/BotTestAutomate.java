package controller.test;

import controller.BotAutomata;
import controller.BotState;
import controller.BotTransition;
import controller.action.BotMove;
import controller.condition.BotTrue;
import model.entity.Entity;
import model.entity.EntityProperties;
import model.entity.EntityType;


public class BotTestAutomate {

	public static void main (String args[]) {
		BotState init = new BotState("init");
		BotAutomata B2 = new BotAutomata("pouet");
		B2.add_state("etat2");

		Entity e = new Entity(0, 0, EntityProperties.COWBOY);

		while (true) {
			e.update(100);
		}

		//System.out.println("zer");
	}

}
