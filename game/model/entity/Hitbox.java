package model.entity;

import java.awt.Polygon;


public class Hitbox {

	private double m_x;
	private double m_y;
	private double m_largeur;
	private double m_hauteur;


	public Hitbox (double x, double y, double lar, double haut) {
		m_x = x;
		m_y = y;
		m_largeur = lar;
		m_hauteur = haut;
	}

	public void move (double dx, double dy) {
		m_x += dx;
		m_y += dy;
//		System.out.println("déplacement en " + m_y + " , " + m_x);
	}
	
	public void move (EntityInterface e) {
		m_x = e.getPosX();
		m_y = e.getPosY();
//		System.out.println("déplacement en " + m_y + " , " + m_x);
	}

	public double getX () {
		return m_x;
	}

	public double getY () {
		return m_y;
	}

}
