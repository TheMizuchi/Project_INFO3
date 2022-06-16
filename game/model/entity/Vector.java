package model.entity;

public class Vector {

	private double m_x;
	private double m_y;

	private boolean m_N;
	private boolean m_W;
	private boolean m_S;
	private boolean m_E;


	public boolean getN () {
		return m_N;
	}

	public boolean getW () {
		return m_W;
	}

	public boolean getS () {
		return m_S;
	}

	public boolean getE () {
		return m_E;
	}

	public void setX (double x) {
		m_x = x;
	}

	public void setY (double y) {
		m_y = y;
	}

	public void decX (double x) {
		m_x -= x;
	}

	public void incX (double x) {
		m_x += x;
	}

	public void decY (double y) {
		m_y -= y;
	}

	public void incY (double y) {
		m_y += y;
	}

	public double getX () {
		return m_x;
	}

	public double getY () {
		return m_y;
	}

	public void update (boolean N, boolean W, boolean S, boolean E) {

		if (N) {
			m_N = !m_N;
		}

		if (W) {
			m_W = !m_W;
		}

		if (S) {
			m_S = !m_S;
		}

		if (E) {
			m_E = !m_E;
		}
	}

}
