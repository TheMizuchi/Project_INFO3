package model.entity;

public class Direction {

	public static final int ORENTATION_WEST = 0;
	public static final int ORENTATION_EST = 1;
	public static final int ORENTATION_NORTH = 2;
	public static final int ORENTATION_SOUTH = 3;

	int m_dir;

	public Direction() {
		m_dir = ORENTATION_NORTH;
	}

	public int getDirection() {
		return m_dir;
	}

	void update_orentaiton(double dx, double dy) {
		if (Math.abs(dx) > Math.abs(dy)) {
			if (dx > 0) {
				m_dir = ORENTATION_EST;
			} else {
				m_dir = ORENTATION_WEST;
			}
		} else {
			if (dy > 0) {
				m_dir = ORENTATION_NORTH;
			} else {
				m_dir = ORENTATION_SOUTH;
			}
		}
	}

}
