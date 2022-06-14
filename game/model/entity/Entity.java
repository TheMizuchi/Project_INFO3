package model.entity;

import model.Model;

public class Entity {

	public int m_ID;
	private Hitbox m_hitbox;
	Direction m_orientation;
	// Liste d'items

	//

	Entity(double x, double y, int ID) {
		m_ID = ID;
		m_hitbox = new Hitbox(x, y, 0.5, 0.5);
	}

	public int getOrientation() {
		return m_orientation.getDirection() % 2;
	}

	public double getX() {
		return m_hitbox.getX();
	}

	public double getY() {
		return m_hitbox.getY();
	}

	void move(double dx, double dy) {
		m_hitbox.move(dx, dy);
		m_orientation.update_orentaiton(dx, dy);
	}

	public void update() {
		this.move(0.5, 0.5);
	}

	void attack() {
	}

	void interact() {
	}

}
