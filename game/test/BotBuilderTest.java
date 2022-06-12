package test;

import java.io.BufferedReader;
import java.io.FileReader;

import controller.BotBuilder;
import info3.game.automata.ast.AST;
import info3.game.automata.parser.AutomataParser;


public class BotBuilderTest {

	public static AST from_file (String path_file) throws Exception {
		AST ast = new AutomataParser(new BufferedReader(new FileReader(path_file))).Run();
		return ast;
	}

	public static AST from_string (String input) throws Exception {
		AST ast = new AutomataParser(new java.io.StringReader(input)).Run();
		return ast;
	}

	public static void main (String[] args) throws Exception {
		BotBuilder bb = new BotBuilder();

		AST ast = from_file("game/test/TestAutomata.gal");
		ast.accept(bb);
		ast.accept(bb);
		System.out.println("");
	}
}
