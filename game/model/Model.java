package model;

import controller.Controller;
import edu.polytech.oop.collections.*;
import edu.polytech.oop.collections.LinkedList.Iterator;
import model.entity.Entity;
import view.MyCanvas;


public class Model {

	public static final int COWBOY_ID = 0;
	public static final int J1_ID = 1;
	public static final int J2_ID = 2;
	public static final int BLOON_ID = 3;
	public static final int ZOMBIE_ID = 4;
	public static final int BAT_ID = 5;
	public static final int DART_MONKEY_ID = 6;
	
	public static final int ENTITY_NUMBER = 7; 
	

	private int m_time_passed;
	private LinkedList m_listeEntity;
	private static Model m_instance = null;
	private Controller m_cont;
	private MyCanvas m_canvas;

	private Model () {
		m_listeEntity = new LinkedList();
		m_cont = Controller.getInstance();
		m_canvas = MyCanvas.getInstance();
		createEntity();
	}

	public static Model getInstance () {
		if (m_instance == null)
			m_instance = new Model();
		return m_instance;
	}

	public void update (long elapsed) {
		m_time_passed += elapsed;

		if (m_time_passed > 100) {
			m_time_passed = 0;
			Iterator it = m_listeEntity.iterator();

			while (it.hasNext()) {
				Entity entity = (Entity) it.next();
				entity.update();
			}
		}
	}

	private void createEntity () {
		m_listeEntity.insertAt(0, new Entity(1, 1, 0));
	}

}
