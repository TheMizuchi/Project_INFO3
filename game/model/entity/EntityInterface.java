package model.entity;

public interface EntityInterface {

	abstract boolean myDir (Direction orientation);
	// retourn si la direciton est la bonne

	abstract boolean cell (Direction orientation, TypeEntity type);
	// l'entité cible se situe devant (orientation) à une étapde de déplacement

	abstract boolean closest (Direction orientation, TypeEntity type);
	// l'entité la plus proche du type est dans cette direciton

	abstract boolean gotPower ();
	// il reste des PV

	abstract boolean gotStuff ();
	// inventaire non vide
	// OPTIONNELLE

	abstract void pop ();

	abstract void wizz ();

	abstract void waitt ();
	// Skip son cout de clock

	abstract void move (Direction orientation);
	// déplacement, pas de retour

	abstract void turn (double orientation, boolean absolute);
	// rotation absolue ou relative suivant la valeur du boolean

	abstract void hit (Direction orientation);

	abstract void protect (Direction orientation);

	abstract void pick (Direction orientation);
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

	abstract void egg (Direction orientation);

	double getPosX ();

	double getPosY ();

	// Renvoie true si l'orientation est comprise entre pi/2 et 3pi/2, false sinon
	int getOrientation ();

}
