package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import view.animation.Texture;


public class MapView extends ViewElement {

	//ICI je pars du principe que la map est affiché par un tableau a deux dimensions de int. Dans le cas ou "carte[][]" est pour une salle seulement alors ont fait une liste de ces salles là.
	int carte[][];
	int nb_salle;
	Texture tex = Texture.loadTexture("resources/textures.png", 1, 4);
	int t_case;


	public MapView (int carte[][]) {
		super(0, 0, 1);
		this.carte = carte;
		this.t_case = (int) (MyCanvas.METRIC_BASE);
	}

	public void setPosition (int x, int y, double scale) {
		this.x = x;
		this.y = y;
		this.scale = scale;
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
	protected void paint (Graphics g) {
		this.t_case = (int) (MyCanvas.METRIC_BASE * this.scale);
		int h = 0;
		int l = 0;
		BufferedImage img;

		for (h = 0; h < this.hauteur(); h++) {

			for (l = 0; l < this.largeur(); l++) {

				switch (carte[h][l]) {
					case 1:
						//noir;
						//g.setColor(Color.black);
						//g.fillRect(x + h_case * h, y + l_case * l, l_case, h_case);
						img = tex.m_images[1];
						break;
					case 2:
						//orange;
						//g.setColor(Color.orange);
						//g.fillRect(x + h_case * h, y + l_case * l, l_case, h_case);
						img = tex.m_images[2];
						break;
					case 3:
						//vert
						//g.setColor(Color.green);
						//g.fillRect(x + h_case * h, y + l_case * l, l_case, h_case);
						img = tex.m_images[3];
						break;
					default:
						//bleu
						//g.setColor(Color.blue);
						//g.fillRect(x + h_case * h, y + l_case * l, l_case, h_case);
						img = tex.m_images[0];
				}
				g.setColor(Color.black);
				g.drawImage(img, (int) (x + this.t_case * (double) ((double) h - (double) this.hauteur() / 2)), (int) (y + this.t_case * (double) ((double) l - (double) this.largeur() / 2)), this.t_case, this.t_case, null);

			}
		}
	}
}
