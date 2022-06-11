package view;

import java.awt.Color;
import java.awt.Graphics;


public class MapView extends ViewElement {

	//ICI je pars du principe que la map est affiché par un tableau a deux dimensions de int. Dans le cas ou "carte[][]" est pour une salle seulement alors ont fait une liste de ces salles là.
	int carte[][];
	int nb_salle;
	int x = 0;
	int y = 0;
	final int l_case = 50;
	final int h_case = 50;


	public MapView (int carte[][]) {
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
		int h = 0;
		int l = 0;

		for (h = 0; h < this.hauteur(); h++) {

			for (l = 0; l < this.largeur(); l++) {

				switch (carte[h][l]) {
					case 1:
						//noir;
						g.setColor(Color.black);
						g.fillRect(x + h_case * h, y + l_case * l, l_case, h_case);
						break;
					case 2:
						//orange;
						g.setColor(Color.orange);
						g.fillRect(x + h_case * h, y + l_case * l, l_case, h_case);
						break;
					case 3:
						//vert
						g.setColor(Color.green);
						g.fillRect(x + h_case * h, y + l_case * l, l_case, h_case);
						break;
					default:
						//bleu
						g.setColor(Color.blue);
						g.fillRect(x + h_case * h, y + l_case * l, l_case, h_case);
						g.setColor(Color.black);
						g.drawRect(x + h_case * h, y + l_case * l, l_case, h_case);

				}

			}
		}
	}

}
