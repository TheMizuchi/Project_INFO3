package model.entity;

public abstract class Direction {

	static final double RACINE_DE_DEUX_SUR_DEUX = 1 / Math.sqrt(2);

	protected Vector m_v;


	public double getX () {
		return m_v.getX();
	}

	public double getY () {
		return m_v.getY();
	}

	abstract void compute ();

	abstract void move (Vector v);

}