package View;

import java.awt.Color;
import java.awt.Graphics;


public class mapView extends ViewElement {

	//ICI je pars du principe que la map est affiché par un tableau a deux dimensions de int. Dans le cas ou "carte[][]" est pour une salle seulement alors ont fait une liste de ces salles là.
	int carte[][];
	int nb_salle;
	int x = 1;
	int y = 1;
	final int l_case = 50;
	final int h_case = 50;
	int h;
	int l;


	public mapView (int carte[][]) {
		this.carte = carte;
	}

	int hauteur () {
		return this.carte.length;
	}

	int largeur () {

		try {
			return this.carte[0].length;
		}
		catch (IllegalArgumentException e) {
			System.out.println("Something went wrong.");
			return -1;
		}
	}

	@Override
	void paint (Graphics g) {

		// TODO Auto-generated method stub
		switch (carte[h][l]) {
			case 0:
				//noir;
				g.drawRect(x * h, y * l, l_case, h_case);
				g.setColor(Color.black);
			case 1:
				//orange;
				g.drawRect(x * h, y * l, l_case, h_case);
				g.setColor(Color.orange);
			case 2:
				//vert
				g.drawRect(x * h, y * l, l_case, h_case);
				g.setColor(Color.green);
			default:
				//gris
				g.drawRect(x * h, y * l, l_case, h_case);
				g.setColor(Color.gray);

		}

	}

}
