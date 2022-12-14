package model.entity;

public interface EntityInterface {

	abstract boolean myDir (double angle, boolean absolute);
	// retourn si la direciton est la bonne

	abstract boolean cell (Vector vect, EntityType type);
	// l'entité cible se situe devant (orientation) à une étapde de déplacemenet

	abstract boolean closest (double orientation, EntityType type);
	// l'entité la plus proche du type est dans cette direciton

	abstract boolean gotPower ();
	// il reste des PV

	abstract boolean gotStuff ();
	// inventaire non vide
	// OPTIONNELLE

	abstract void pop ();

	abstract void wizz ();

	abstract void move (Direction orientation);
	// déplacement, pas de retour

	abstract void turn (double orientation, boolean absolute);
	// rotation absolue ou relative suivant la valeur du boolean

	abstract void hit (Vector vec);

	abstract void protect (Direction orientation);

	abstract void pick ();
	// ramasser un object

	abstract void put (Direction orientation);
	// placer un objet

	abstract void store ();
	// passage de la main à l'inventaire
	// OPTIONNELLE

	abstract void get ();
	// passage de l'inventaire à la main
	// OPTIONNELLE

	abstract void power ();
	// récupération de PV
	// UNIQUEMENT FIN DE ETAGE (pas de potions)

	abstract void explode ();
	// MORT

	abstract void egg (double orientationx, double orientationy);

	double getPosX ();

	double getPosY ();

	// Renvoie true si l'orientation est comprise entre pi/2 et 3pi/2, false sinon
	int getOrientation ();

}
