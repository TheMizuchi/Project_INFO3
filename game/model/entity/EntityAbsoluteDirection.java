package model.entity;

public class EntityAbsoluteDirection extends Direction {

	private double m_angle;


	public EntityAbsoluteDirection (double angle) {
		m_angle = angle;
		m_v = new Vector();
	}

	public void setAngle (double angle) {
		m_angle = angle;
	}

	@Override
	protected void compute () {
		double x = Math.cos(m_angle);
		double y = Math.sin(m_angle);
		x = (Math.abs(x) > 0.0000001) ? (x) : (0);
		y = (Math.abs(y) > 0.0000001) ? (y) : (0);
		m_v.setX(x);
		m_v.setY(-y);
	}

	@Override
	boolean move (Vector dirEntity) {

		// On teste s'il y avait un mouvement sur l'axe x et y
		boolean xWasMoving = dirEntity.getX() != 0;
		boolean yWasMoving = dirEntity.getY() != 0;
		boolean wasMoving = xWasMoving || yWasMoving;

		compute();

		// On récupére le vecteur envoyé par la nouvelle destination
		double dirX = m_v.getX();
		double dirY = m_v.getY();

		// On teste s'il y a un mouvement sur l'axe x et y
		boolean xMoving = m_v.getX() != 0;
		boolean yMoving = m_v.getY() != 0;
		boolean isMoving = xMoving || yMoving;

		// Initialement on ne bouge pas puis on utilise xmove et ymove pour définir le mouvement
		dirEntity.setX(m_v.getX());
		dirEntity.setY(m_v.getY());
		//		dirEntity.updateAngle();
		dirEntity.setAngle(m_angle);
		return isMoving ^ wasMoving;
	}

}
