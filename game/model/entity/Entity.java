package model.entity;

import controller.RefAutomata;
import model.Model;


public class Entity {

	public int m_ID;
	private Hitbox m_hitbox;
	Direction m_orientation;
	protected RefAutomata m_automata;

	// Liste d'items

	//


	public Entity (double x, double y, int ID) {
		m_ID = ID;
		m_orientation = new Direction();
		m_hitbox = new Hitbox(x, y, 0.5, 0.5);
		m_automata = new RefAutomata(this);
	}

	public int getOrientation () {
		return m_orientation.getDirection() % 2;
	}

	public double getX () {
		return m_hitbox.getX();
	}

	public double getY () {
		return m_hitbox.getY();
	}

	public void move (double dx, double dy) {
		m_hitbox.move(dx, dy);
		m_orientation.update_orentaiton(dx, dy);
	}

	public void update () {
		m_automata.step();
	}

	void attack () {}

	void interact () {}

}
