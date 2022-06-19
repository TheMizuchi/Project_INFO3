package model.entity;

public class EntityRelativeDirection extends EntityAbsoluteDirection {

	public EntityRelativeDirection (Entity e, double angle) {
		super(angle + e.getDirVector().getAngle());
	}

	@Override
	boolean move (Vector dirEntity) {

		// On teste s'il y avait un mouvement sur l'axe x et y
		boolean xWasMoving = dirEntity.getX() != 0;
		boolean yWasMoving = dirEntity.getY() != 0;
		boolean wasMoving = xWasMoving || yWasMoving;

		dirEntity.setApply(true);
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
		return isMoving ^ wasMoving;
	}
}