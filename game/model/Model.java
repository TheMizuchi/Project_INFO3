package model;

import edu.polytech.oop.collections.*;
import edu.polytech.oop.collections.LinkedList.Iterator;
import model.entity.Entity;

public class Model {

	public static final int COWBOY_ID = 0;

	private int m_time_passed;
	private LinkedList m_listeEntity;
	private static Model m_instance = null;

	private Model() {
		m_listeEntity = new LinkedList();
	}

	public static Model getInstance() {
		if (m_instance != null)
			m_instance = new Model();
		return m_instance;
	}

	public void update(long elapsed) {
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

}
