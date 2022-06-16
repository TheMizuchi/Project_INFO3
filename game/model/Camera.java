package model;

import model.entity.EntityInterface;
import view.Viewport;

public class Camera {
	
	private Viewport vp;
	private EntityInterface j1;
	private EntityInterface j2;
	
	public Camera(Viewport vp) {
		this.vp = vp;
	}
	
	public void update() {
		double dx = Math.abs(j1.getPosX()-j2.getPosX());
		double dy = Math.abs(j1.getPosY()-j2.getPosY());
		double scale = 1;
		this.setPosition(dx/2, dy/2, scale);
	}
	
	//passer en private quand on aura les deux joueurs
	public void setPosition(double x, double y, double scale) {
		this.vp.setPosition(x, y, scale);
	}
}
