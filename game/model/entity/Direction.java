package model.entity;

public abstract class Direction {

	protected int m_x;
	protected int m_y;
	
	public double getX() {
		return m_x; 
	}

	public double getY() {
		return m_y;
	}
	
	abstract void compute();
	
	abstract void move();
	
}