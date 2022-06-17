package controller.test;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import controller.BotAutomata;
import controller.BotState;
import model.Model;
import model.entity.Entity;
import model.entity.EntityProperties;


public class BotTestAutomate {

	public static void main (String args[]) throws ParseException, IOException {
		Model m = new Model();
		BotState init = new BotState("init");
		BotAutomata B2 = new BotAutomata("pouet");
		B2.add_state("etat2");

		Entity e = new Entity(m, 0, 0, EntityProperties.COWBOY);

		while (true) {
			e.update(100);
		}

		//System.out.println("zer");
	}

}
