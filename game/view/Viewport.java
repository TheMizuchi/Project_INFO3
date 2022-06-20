package view;

public class Viewport {

	private static final double SCALE_BASE = 4;
	private double x, y, scale;
	private int w, h;

	/*
	 * Méthodes public pour la caméra du model
	 */


	public Viewport (int width, int height) {
		this.w = width;
		this.h = height;
		this.x = 0;
		this.y = 0;
		this.scale = SCALE_BASE;
	}

	public void setPosition (double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void setPosition (double x, double y, double s) {
		this.scale = s * SCALE_BASE;
		this.setPosition(x, y);
	}

	/*
	 * Méthodes pour la view
	 */

	public double getScale () {
		return this.scale;
	}

	public boolean isInside (int x, int y, int w, int h) { //récupérer des paramètres pour connaitre la largeur et la hauteur du sprite de l'entité en pixel pour pouvoir les désafficher uniquement si l'image est entièrement en dehors de l'écran
		int local_w = (int) (w / 2 * this.scale) - 1;
		int local_h = (int) (h / 2 * this.scale) - 1;
		return x + local_w >= 0 && x - local_w < this.w && y + local_h >= 0 && y - local_h < this.h;

	}

	public int toLocalX (double x) {
		//TODO Retourne la coordonnée X locale au Viewport depuis la coordonné x venant du model

		return this.w / 2 + (int) ((x - this.x) * this.scale * MyCanvas.METRIC_BASE);
	}

	public int toLocalY (double y) {
		//TODO Retourne la coordonnée Y locale au Viewport depuis la coordonné y venant du model
		return this.h / 2 + (int) ((y - this.y) * this.scale * MyCanvas.METRIC_BASE);
	}

}
