package controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import edu.polytech.oop.collections.ArrayList;
import edu.polytech.oop.collections.IList;
import edu.polytech.oop.collections.LinkedList;
import info3.game.automata.ast.AST;
import info3.game.automata.parser.AutomataParser;
import info3.game.automata.parser.ParseException;
import model.Model;
import view.MenuFrame;
import model.entity.EntityProperties;


public class Controller {

	Model m_model;
	IList m_auts;
	boolean m_dirKeys[] = new boolean[256];
	boolean m_keys[] = new boolean[256];
	boolean m_keysPrev[] = new boolean[256];
	IList m_keysToUpdate;


	private BotAutomata getAutFromFile (String filePath) throws Exception {
		BotBuilder bb = BotBuilder.getInstance();
		return (BotAutomata) ((IList) from_file(filePath).accept(bb)).elementAt(0);
	}

	private void insertAt (IList aut, int index, Object o) {

		try {
			aut.updateAt(index, o);
		}
		catch (Exception ex) {
			aut.insertAt(index, o);
		}
	}

	private Controller () {
		m_auts = new ArrayList();

		try {
			BotAutomata moveFoward = getAutFromFile("resources/Automata/MoveFoward.gal");
			BotAutomata moveKeys = getAutFromFile("resources/Automata/MoveKeys.gal");
			BotAutomata moveKeysArrows = getAutFromFile("resources/Automata/MoveKeysArrows.gal");
			BotAutomata moveRandom = getAutFromFile("resources/Automata/MoveRandom.gal");
			BotAutomata moveRandomUnderscoreState = getAutFromFile("resources/Automata/MoveRandomUnderscoreState.gal");
			BotAutomata moveRelativeKeys = getAutFromFile("resources/Automata/MoveRelativeKeys.gal");
			BotAutomata moveSquare = getAutFromFile("resources/Automata/MoveSquare.gal");
			BotAutomata moveBigSquare = getAutFromFile("resources/Automata/MoveBigSquare.gal");
			BotAutomata moveOuestThenLeft = getAutFromFile("resources/Automata/MoveOuestThenLeft.gal");
			BotAutomata torch = getAutFromFile("resources/Automata/Torch.gal");
			BotAutomata idle = getAutFromFile("resources/Automata/Idle.gal");

			BotAutomata J1 = getAutFromFile(MenuFrame.getFileJ1());
			BotAutomata J2 = getAutFromFile(MenuFrame.getFileJ2());
			BotAutomata Bloon = getAutFromFile(MenuFrame.getFileBloon());
			BotAutomata Bat = getAutFromFile(MenuFrame.getFileBat());
			BotAutomata Skeleton = getAutFromFile(MenuFrame.getFileSkeleton());

			BotAutomata doge = getAutFromFile("resources/Automata/Doge.gal");
			BotAutomata mystery = getAutFromFile("resources/Automata/Mystery.gal");

			// Mobs
			BotAutomata EntityTurnTest = getAutFromFile("resources/Automata/EntityTurnTest.gal");

			insertAt(m_auts, EntityProperties.COWBOY.getID(), moveSquare);
			insertAt(m_auts, EntityProperties.J1.getID(), J1);
			insertAt(m_auts, EntityProperties.J2.getID(), J2);
			insertAt(m_auts, EntityProperties.BLOON.getID(), Bloon);
			insertAt(m_auts, EntityProperties.SKELETON.getID(), Skeleton);
			insertAt(m_auts, EntityProperties.BAT.getID(), Bat);
			insertAt(m_auts, EntityProperties.DART_MONKEY.getID(), moveSquare);
			insertAt(m_auts, EntityProperties.TORCH.getID(), torch);
			insertAt(m_auts, EntityProperties.DOGE.getID(), doge);
			insertAt(m_auts, EntityProperties.MYSTERY.getID(), mystery);

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

		if (checkKey((char) e.getKeyCode())) {
			setKey((char) e.getKeyCode(), true);
		}
	}

	public void keyReleased (KeyEvent e) {

		if (checkKey((char) e.getKeyCode())) {
			setKey((char) e.getKeyCode(), false);
		}
	}

	private boolean checkKey (char c) {
		return 0 <= c && c < 256;
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
