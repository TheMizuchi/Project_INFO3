package controller;

import info3.game.automata.ast.AST;
import info3.game.automata.parser.AutomataParser;


public class BotBuilderTest {

	public static AST from_string (String input) throws Exception {
		AST ast = new AutomataParser(new java.io.StringReader(input)).Run();
		return ast;
	}

	public static void main (String[] args) throws Exception {
		BotBuilder bb = new BotBuilder();

		AST ast = from_string("Aut(Idle){ * (Idle): | True ? Move(NE) : (Idle)}");
		ast.accept(bb);
		System.out.println("");
	}
}
