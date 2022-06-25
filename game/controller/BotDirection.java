package controller;

public class BotDirection {

	private double m_angle;
	private boolean m_absolute;
	private boolean m_here;


	public BotDirection (String s) {
		m_angle = 0;
		m_absolute = true;
		m_here = false;

		double ang = Math.PI / 4;

		switch (s) {
			// Rotation relative
			case "R":
				m_angle += 2 * ang;
			case "B":
				m_angle += 2 * ang;
			case "L":
				m_angle += 2 * ang;
			case "F":
			case "H":
				m_here = true;
				m_absolute = false;
				break;

			// Rotation absolue
			case "SE":
				m_angle += ang;
			case "S":
				m_angle += ang;
			case "SW":
				m_angle += ang;
			case "W":
				m_angle += ang;
			case "NW":
				m_angle += ang;
			case "N":
				m_angle += ang;
			case "NE":
				m_angle += ang;
			case "E":
				break;
		}
	}

	public double getAngle () {
		return m_angle;
	}

	public void setAngle (double angle) {
		m_angle = angle;
	}

	public boolean getAbs () {
		return m_absolute;
	}

	public boolean getHere () {
		return m_here;
	}

}
