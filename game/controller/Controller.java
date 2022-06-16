package controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import edu.polytech.oop.collections.IList;
import edu.polytech.oop.collections.LinkedList;
import info3.game.automata.ast.AST;
import info3.game.automata.parser.AutomataParser;
import info3.game.automata.parser.ParseException;
import model.Model;


public class Controller {

	Model m_model;
	IList m_auts;
	boolean m_dirKeys[] = new boolean[256];
	boolean m_keys[] = new boolean[256];
	boolean m_keysPrev[] = new boolean[256];
	IList m_keysToUpdate;


	private Controller () {
		m_auts = new LinkedList();
		BotBuilder bb = BotBuilder.getInstance();

		try {
			AST ast = from_file("resources/Automata/MoveKeys+Pop.gal");
			m_auts.insertAt(0, ((IList) ast.accept(bb)).elementAt(0));
			ast = from_file("resources/Automata/MoveKeys+Pop.gal");
			m_auts.insertAt(m_auts.length(), ((IList) ast.accept(bb)).elementAt(0));
			ast = from_file("resources/Automata/MoveKeysArrows+Pop.gal");
			m_auts.insertAt(m_auts.length(), ((IList) ast.accept(bb)).elementAt(0));
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

		m_keysToUpdate = new LinkedList();
		m_dirKeys['Z'] = true;
		m_dirKeys['Q'] = true;
		m_dirKeys['S'] = true;
		m_dirKeys['D'] = true;

		m_dirKeys[37] = true;
		m_dirKeys[38] = true;
		m_dirKeys[39] = true;
		m_dirKeys[40] = true;
	}

	public void setModel () {
		m_model = Model.getInstance();
	}


	private static Controller INSTANCE = null;


	public static Controller getInstance () {

		if (INSTANCE == null) {
			INSTANCE = new Controller();
		}
		return INSTANCE;
	}

	private static AST from_file (String path_file) throws Exception {
		AST ast = new AutomataParser(new BufferedReader(new FileReader(path_file))).Run();
		return ast;
	}

	private static AST from_string (String input) throws Exception {
		AST ast = new AutomataParser(new java.io.StringReader(input)).Run();
		return ast;
	}

	BotAutomata getAut (int id) {
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
		setKey((char) e.getKeyCode(), true);
	}

	public void keyReleased (KeyEvent e) {
		setKey((char) e.getKeyCode(), false);
	}

	// A appeler dans le model juste AVANT la boucle qui force les automates à step
	public void transfertTab () {
		IList.Iterator ite = m_keysToUpdate.iterator();
		char c;

		while (ite.hasNext()) {
			c = (char) ite.next();
			setKeyPrev(c, !getKeyPrev(c));
		}
		m_keysToUpdate = new LinkedList();
	}

	public boolean getKey (char c) {
		return m_keys[c];
	}

	public boolean getKeyPrev (char c) {
		return m_keysPrev[c];
	}

	public void useKey (char c) {

		if (!m_keysToUpdate.contains(c)) {
			m_keysToUpdate.insertAt(0, c);
		}
	}

	public boolean isdir (char c) {
		return m_dirKeys[c];
	}

	public void setKey (char c, boolean state) {
		m_keys[c] = state;
	}

	public void setKeyPrev (char c, boolean state) {
		m_keysPrev[c] = state;
	}
}
