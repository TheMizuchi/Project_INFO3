package view;

import java.awt.Graphics;


public class MapView extends ViewElement {

	//ICI je pars du principe que la map est affiché par un tableau a deux dimensions de int. Dans le cas ou "carte[][]" est pour une salle seulement alors ont fait une liste de ces salles là.
	int carte[][];
	int nb_salle;


	int hauteur (int m[][]) {
		return m.length;
	}

	int largeur (int m[][]) {

		try {
			return m[0].length;
		}
		catch (IllegalArgumentException e) {
			System.out.println("Something went wrong.");
			return -1;
		}
	}

	@Override
	void paint (Graphics g) {
		// TODO Auto-generated method stub

	}

}
