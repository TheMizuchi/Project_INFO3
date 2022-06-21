package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import model.map.Case;
import view.animation.Texture;


public class MapView extends ViewElement {

	//ICI je pars du principe que la map est affiché par un tableau a deux dimensions de cases. Dans le cas ou "carte[][]" est pour une salle seulement alors ont fait une liste de ces salles là.
	private Case[][] carte;
	private Texture tex = Texture.loadTexture("resources/textures.png", 1, 4);
	private double t_case;


	public MapView (Case[][] cases) {
		super(0, 0, 1);
		this.carte = cases;
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
		this.t_case = (MyCanvas.METRIC_BASE * this.scale);
		int h = 0;
		int l = 0;
		BufferedImage img;

		for (h = 0; h < this.hauteur(); h++) {

			for (l = 0; l < this.largeur(); l++) {

				switch (carte[h][l].getType().getTextureID()) {
					case 1:
						img = tex.m_images[1];
						break;
					case 2:
						img = tex.m_images[2];
						break;
					case 3:
						img = tex.m_images[3];
						break;
					default:
						img = tex.m_images[0];
				}
				g.setColor(Color.black);
				g.drawImage(img, (int) (x + this.t_case * (double) h), (int) (y + this.t_case * (double) l), (int) this.t_case, (int) this.t_case, null);
			}
		}
	}
}
