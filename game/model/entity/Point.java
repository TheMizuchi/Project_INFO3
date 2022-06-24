package model.entity;

import java.awt.Color;
import java.awt.Graphics;

import model.Camera;
import model.Model;


public class Point {

	private double m_x;
	private double m_y;


	public Point (double x, double y) {
		m_x = x;
		m_y = y;
	}

	public double getX () {
		return m_x;
	}

	public double getY () {
		return m_y;
	}

	public void setX (double x) {
		m_x = x;
	}

	public void setY (double y) {
		m_y = y;
	}

	public void paint (Graphics g) {
		Camera c = Model.m_cam;
		g.setColor(Color.white);
		int x = c.vp.toLocalX(m_x);
		int y = c.vp.toLocalY(m_y);
		g.drawRect(x, y, 1, 1);
	}//bidouille ultra temporaire

	public void add (Point p) {
		m_x += p.getX();
		m_y += p.getY();
	}

	public void sub (Point p) {
		m_x -= p.getX();
		m_y -= p.getY();
	}

	public boolean outMap () {
		return !(m_x > 0 && m_y > 0 && Model.getMap().getWidth() > m_x && Model.getMap().getHeight() > m_y);
	}
}
