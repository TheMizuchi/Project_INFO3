package view;


public class Viewport {
	
	double x, y, scale;
	
	
	public Viewport() {
		this.x = 0;
		this.y = 0;
		this.scale = 1;
	}
	
	void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	void setPosition(double x, double y, double scale) {
		this.scale = scale;
		this.setPosition(x, y);
	}
	
	boolean isInside(double x, double y) {
		//TODO Indique si une coordonnée est dans le Viewport en fonction de x, y, et scale
		return false;
	}
	
	int toLocalX(double x) {
		//TODO Retourne la coordonnée X locale au Viewport depuis la coordonné x venant du model
		return 0;
	}
	
	int toLocalY(double y) {
		//TODO Retourne la coordonnée Y locale au Viewport depuis la coordonné y venant du model
		return 0;
	}

}
