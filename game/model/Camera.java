package model;

import view.Viewport;

public class Camera {
	
	private Viewport vp;
	
	public Camera(Viewport vp) {
		this.vp = vp;
	}
	
	void setPosition(double x, double y) {
		this.vp.setPosition(x, y);
	}
	
	void setPosition(double x, double y, double scale) {
		this.vp.setPosition(x, y, scale);
	}
}
