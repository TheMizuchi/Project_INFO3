package controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import edu.polytech.oop.collections.IList;
import edu.polytech.oop.collections.LinkedList;
import info3.game.automata.ast.AST;
import info3.game.automata.parser.AutomataParser;
import info3.game.automata.parser.ParseException;
import model.Model;


public class Controller {

	Model m_model;
	IList m_auts;
	boolean tab[] = new boolean[26];
	public boolean tab_prev[] = new boolean[26];


	private Controller () {
		m_auts = new LinkedList();
		BotBuilder bb = BotBuilder.getInstance();

		try {
			AST ast = from_file("resources/Automata/MoveKeys+Pop.gal");
			m_auts.insertAt(0, ((IList) ast.accept(bb)).elementAt(0));
		}
		catch (ParseException ex) {
			throw new RuntimeException("Erreur de parsing");
		}
		catch (FileNotFoundException ex) {
			throw new RuntimeException("Le fichier n'existe pas");
		}
		catch (Exception ex) {
			throw new RuntimeException("Erreur inconnue dans l'initialisation des automates du controller");
		}

	}

	public void setModel () throws IOException, org.json.simple.parser.ParseException {
		m_model = Model.getInstance();
	}


	private static Controller INSTANCE = null;


	public static Controller getInstance () {

		if (INSTANCE == null) {
			INSTANCE = new Controller();
		}
		return INSTANCE;
	}

	public static AST from_file (String path_file) throws Exception {
		AST ast = new AutomataParser(new BufferedReader(new FileReader(path_file))).Run();
		return ast;
	}

	public static AST from_string (String input) throws Exception {
		AST ast = new AutomataParser(new java.io.StringReader(input)).Run();
		return ast;
	}

	public BotAutomata getAut (int id) {
		return (BotAutomata) m_auts.elementAt(id);
	}

	public void mouseClicked (MouseEvent e) {}

	public void mousePressed (MouseEvent e) {}

	public void mouseReleased (MouseEvent e) {}

	public void mouseEntered (MouseEvent e) {}

	public void mouseExited (MouseEvent e) {}

	public void mouseDragged (MouseEvent e) {}

	public void mouseMoved (MouseEvent e) {}

	public void keyTyped (KeyEvent e) {}

	public void keyPressed (KeyEvent e) {
		int tmp = e.getKeyCode() - 65;

		if (0 <= tmp && tmp < 26) {
			tab[tmp] = true;
		}
	}

	public void keyReleased (KeyEvent e) {
		int tmp = e.getKeyCode() - 65;

		if (0 <= tmp && tmp < 26) {
			tab[tmp] = false;
		}
	}

	// A appeler dans le model juste AVANT la boucle qui force les automates à step
	public void transfertTab () {
		tab_prev = tab;
	}

	public boolean[] getTabKeys () {
		return tab;
	}

	public boolean[] getTabKeys_prev () {
		return tab_prev;
	}

	public void affTabKeys () {

		System.out.println("\n");
		for (int i = 0; i < 26; i++)
			System.out.println(tab[i]);
		System.out.println("\n");
	}
}
