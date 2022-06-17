package model.entity;

public class PlayerRelativeDirection extends PlayerAbsoluteDirection {

	public PlayerRelativeDirection (Entity e, double angle) {
		super(angle + e.getDirVector().getAngle());
	}

	@Override
	boolean move (Vector dirEntity) {

		// On teste s'il y avait un mouvement sur l'axe x et y
		boolean xWasMoving = dirEntity.getW() ^ dirEntity.getE();
		boolean yWasMoving = dirEntity.getN() ^ dirEntity.getS();
		boolean wasMoving = xWasMoving || yWasMoving;

		compute();

		// On récupére le vecteur envoyé par la nouvelle destination
		double dirX = m_v.getX();
		double dirY = m_v.getY();

		// On met à jour les boolean d'orientation contenu dans le vecteur direction de l'entité
		if (dirX < 0) {
			dirEntity.update(false, true, false, false);
		} else if (dirX > 0) {
			dirEntity.update(false, false, false, true);
		}

		if (dirY < 0) {
			dirEntity.update(true, false, false, false);
		} else if (dirY > 0) {
			dirEntity.update(false, false, true, false);
		}

		// On teste s'il y a un mouvement sur l'axe x et y
		boolean xMoving = dirEntity.getW() ^ dirEntity.getE();
		boolean yMoving = dirEntity.getN() ^ dirEntity.getS();
		boolean isMoving = xMoving || yMoving;

		// Initialement on ne bouge pas puis on utilise xmove et ymove pour définir le mouvement
		dirEntity.setX(0);
		dirEntity.setY(0);

		if (xMoving && yMoving) {
			// S'il y a un mouvement sur les 2 axes il faut trouver quelle diagonale pour affection la bonne vitesse
			dirEntity.setY((dirEntity.getN()) ? (-RACINE_DE_DEUX_SUR_DEUX) : (RACINE_DE_DEUX_SUR_DEUX));
			dirEntity.setX((dirEntity.getW()) ? (-RACINE_DE_DEUX_SUR_DEUX) : (RACINE_DE_DEUX_SUR_DEUX));
		} else if (xMoving) {

			// S'il y a seulement un mouvement suivant x il suffit de trouver dans quel sens de l'axe
			if (dirEntity.getW()) {
				dirEntity.setX(-1);
			} else {
				dirEntity.setX(1);
			}
		} else if (yMoving) {

			// S'il y a seulement un mouvement suivant y il suffit de trouver dans quel sens de l'axe
			if (dirEntity.getN()) {
				dirEntity.setY(-1);
			} else {
				dirEntity.setY(1);
			}
		} else {
			return isMoving ^ wasMoving;
		}
		return isMoving ^ wasMoving;
	}
}