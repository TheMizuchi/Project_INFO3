package model.entity;

public class Vector {

	private double m_x;
	private double m_y;

	// Used only by players
	private boolean m_N;
	private boolean m_W;
	private boolean m_S;
	private boolean m_E;

	private double m_angle;

	private boolean m_apply;


	public Vector clone () {
		Vector vec = new Vector();
		vec.m_x = m_x;
		vec.m_y = m_y;
		vec.m_N = m_N;
		vec.m_W = m_W;
		vec.m_S = m_S;
		vec.m_E = m_E;
		vec.m_angle = m_angle;
		vec.m_apply = m_apply;
		return vec;
	}

	public boolean isApplied () {
		return m_apply;
	}

	public void setApply (boolean apply) {
		m_apply = apply;
	}

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

	// Used only by players
	public double updatePlayerAngle () {
		boolean xMove = getW() ^ getE();
		boolean yMove = getN() ^ getS();

		m_angle = 0;

		if (xMove && yMove) {

			if (getN()) {

				if (getW()) {
					m_angle = 3 * Math.PI / 4;
				} else {
					m_angle = Math.PI / 4;
				}
			} else {

				if (getW()) {
					m_angle = 5 * Math.PI / 4;
				} else {
					m_angle = 7 * Math.PI / 4;
				}
			}
		} else if (xMove) {

			if (getW()) {
				m_angle = Math.PI;
			} else {
				m_angle = 0;
			}
		} else if (yMove) {

			if (getN()) {
				m_angle = Math.PI / 2;
			} else {
				m_angle = 3 * Math.PI / 2;
			}
		}
		return m_angle;
	}

	public double getAngle () {

		while (m_angle >= 2 * Math.PI) {
			m_angle -= 2 * Math.PI;
		}
		return m_angle;
	}

	public void setAngle (double angle) {
		m_angle = angle;
	}

}
