package model.map.generator;

public class RectangleCollisionTEMPORAIRE {

	/*
	 * Cette classe est temporaire, elle sert à placer les salles sans qu'elles ne
	 * se chevauchent. On pourra sûrement la remplacer par la classe Collision
	 * développée pour les Entity
	 */

	/*
	 * Cette classe à une vision discète de la collsion de rectangles
	 */

	RectangleTEMPORAIRE r1, r2;


	public RectangleCollisionTEMPORAIRE (RectangleTEMPORAIRE r1, RectangleTEMPORAIRE r2) {
		this.r1 = r1;
		this.r2 = r2;
	}

	public boolean collides () {
		return r1.x < r2.x + r2.w && r1.x + r1.w > r2.x && r1.y < r2.y + r2.h && r1.h + r1.y > r2.y;
	}

}
