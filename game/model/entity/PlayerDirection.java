package model.entity;

public class PlayerDirection {

	private int m_dirX;
	private int m_dirY;

	private boolean m_N;
	private boolean m_S;
	private boolean m_E;
	private boolean m_W;

	private double m_angle;


	public PlayerDirection () {
		m_dirX = 0;
		m_dirY = 0;
		m_angle = Math.PI / 2;
	}

	public int getDirectionX () {
		return m_dirX;
	}

	public int getDirectionY () {
		return m_dirY;
	}

	public void faceNorth () {
		m_dirY = -1;
	}

	public void faceSouth () {
		m_dirY = 1;
	}

	public void faceEst () {
		m_dirX = 1;
	}

	public void faceWest () {
		m_dirX = -1;
	}

	public void updateDirection (PlayerDirection dir) {
		int dirX = dir.getDirectionX();
		int dirY = dir.getDirectionY();

		if (dirY == -1) {
			m_N = !m_N;
		} else if (dirY == 1) {
			m_S = !m_S;
		}
		m_dirY = 0;

		if (m_N) {
			m_dirY -= 1;
		}

		if (m_S) {
			m_dirY += 1;
		}

		if (dirX == -1) {
			m_W = !m_W;
		} else if (dirX == 1) {
			m_E = !m_E;
		}
		m_dirX = 0;

		if (m_W) {
			m_dirX -= 1;
		}

		if (m_E) {
			m_dirX += 1;
		}

	}

	public void setAngle (double angle) {
		m_angle = angle;
	}

	public double getAngle () {
		return m_angle;
	}

	public double toAngle () {
		double angle;

		if (m_dirX == 0) { // dirX ==0
			return angle = Math.PI / 2 * m_dirY;
		}
		angle = Math.atan(m_dirY / m_dirX);

		if (m_dirX > 0) {
			return angle;
		} else { // (m_dirX<0)
			return (angle + Math.PI) % (Math.PI * 2);
		}
	}
}