package view;

public class Viewport {

	private static final double SCALE_BASE = 2;
	double x, y, scale;
	int w, h;


	public Viewport (int width, int height) {
		this.w = width;
		this.h = height;
		this.x = 0;
		this.y = 0;
		this.scale = 1;
	}

	void setPosition (double x, double y) {
		this.x = (x);
		this.y = (y);
	}

	void setPosition (double x, double y, double s) {
		this.scale = s * SCALE_BASE;
		this.setPosition(x, y);
	}

	boolean isInside (int x, int y) {
		//TODO Indique si une coordonnée est dans le Viewport
		return x>=this.x && x<this.x+this.w && y>=this.y && y<this.y+this.h;
	}

	int toLocalX (double x) {
		//TODO Retourne la coordonnée X locale au Viewport depuis la coordonné x venant du model

		return this.w / 2 + (int) ((x - this.x) * this.scale);
	}

	int toLocalY (double y) {
		//TODO Retourne la coordonnée Y locale au Viewport depuis la coordonné y venant du model
		return this.h / 2 + (int) ((y - this.y) * this.scale);
	}

}
