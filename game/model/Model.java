package model;

import controller.Controller;
import edu.polytech.oop.collections.*;
import edu.polytech.oop.collections.LinkedList.Iterator;
import model.entity.Entity;


public class Model {

	public static final int COWBOY_ID = 0;

	private int m_time_passed;
	private LinkedList m_listeEntity;
	private static Model m_instance = null;
	private Controller m_cont;


	private Model () {
		m_listeEntity = new LinkedList();
		createEntity();
		m_cont = Controller.getInstance();
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
