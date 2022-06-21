package model;

import model.entity.Entity;
import model.entity.EntityInterface;
import view.Viewport;


public class Camera {

	private Viewport vp;
	private EntityInterface j1;
	private EntityInterface j2;
	static boolean bloquer = false;
	static double scale;


	public Camera (Viewport vp, Entity j1, Entity j2, double x, double y) {
		this(vp, x, y);
		this.j1 = j1;
		this.j2 = j2;
	}

	public Camera (Viewport vp, double x, double y) {
		this.vp = vp;
		this.setPosition(x, y, 1);
	}

	public void update () {
		double dx = Math.abs(j1.getPosX() - j2.getPosX());
		double dy = Math.abs(j1.getPosY() - j2.getPosY());
		scale = Math.min(Math.min(13 / Math.max(dx, 13), 5 / Math.max(dy, 5)), 1);
		scale = Math.max(scale, 0.75);
		if (scale <0.76) {
			bloquer = true;
		}
		System.out.println("la scale "+scale);
		this.setPosition((double) (j1.getPosX() + j2.getPosX()) / 2, (double) (j1.getPosY() + j2.getPosY()) / 2, scale);
	}

	public void setj1 (Entity j) {
		this.j1 = j;
	}

	public void setj2 (Entity j) {
		this.j2 = j;
	}

	private void setPosition (double x, double y, double scale) {
		this.vp.setPosition(x, y, scale);
	}
	
	public static boolean getBlock() {
		return bloquer;
	}
	
	public static double getScale() {
		return scale;
	}
}
