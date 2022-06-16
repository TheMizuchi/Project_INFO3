package model.entity;

public class Direction {

	private int m_dirX;
	private int m_dirY;
	
	//private boolean m_actif ; 
	
	private double m_angle; 

	public Direction() {
		m_dirX = 0;
		m_dirY = 0;
		m_angle = Math.PI/2;
		//m_actif = false ; 
	}
	
	//public void setActif(boolean actif) {
	//	m_actif = actif; 
	//}

	public int getDirectionX() {
		return m_dirX;
	}

	public int getDirectionY() {
		return m_dirY;
	}

	public void faceNorth() {
		m_dirY = -1;
	}

	public void faceSouth() {
		m_dirY = 1;
	}

	public void faceEst() {
		m_dirX = 1;
	}

	public void faceWest() {
		m_dirX = -1;
	}

	public void updateDirection(Direction dir) {
		int dirX = dir.getDirectionX();
		int dirY = dir.getDirectionY();

		if (m_dirX == dirX) {
			m_dirX = 0;
		} else {
			if (dirX != 0) {
				m_dirX = dirX;
			}
		}

		if (m_dirY == dirY) {
			m_dirY = 0;
		} else {
			if (dirY != 0) {
				m_dirY = dirY;
			}
		}
	}
	
	public void setAngle(double angle) {
		m_angle= angle ; 
	}
	
	public double getAngle() {
		return m_angle; 
	}

	public double toAngle() {
		double angle;
		if(m_dirX==0) { // dirX ==0
			return angle = Math.PI/2*m_dirY;
		}
		angle = Math.atan(m_dirY/m_dirX);
		
		if (m_dirX >0) {
			return angle ; 
		}else { // (m_dirX<0)
			return (angle+Math.PI) % (Math.PI*2);
		}
	}
}
