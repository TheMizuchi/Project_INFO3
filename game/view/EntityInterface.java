package view;


public interface EntityInterface {
	int getPosX();
	int getPosy();
	
	// Renvoie true si l'orientation est comprise entre pi/2 et 3pi/2, false sinon
	int getOrientation();
}
