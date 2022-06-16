package model;

import model.entity.Entity;
import model.entity.EntityInterface;
import view.Viewport;

public class Camera {
	
	private Viewport vp;
	private EntityInterface j1;
	private EntityInterface j2;
	
	public Camera(Viewport vp, Entity j1, Entity j2) {
		this(vp);
		this.j1 = j1;
		this.j2 = j2;
	}
	
	public Camera(Viewport vp) {
		this.vp = vp;
	}
	
	public void update() {
		double dx = Math.abs(j1.getPosX()-j2.getPosX());
		double dy = Math.abs(j1.getPosY()-j2.getPosY());
		double scale = Math.max(Math.max(Math.max(dx, 13)/13, Math.max(dy, 9)/9), 1);
		this.setPosition(dx/2, dy/2, scale);
	}
	
	public void setj1(Entity j) {
		this.j1 = j;
	}
	
	public void setj2(Entity j) {
		this.j2 = j;
	}
	
	//passer en private quand on aura les deux joueurs
	public void setPosition(double x, double y, double scale) {
		this.vp.setPosition(x, y, scale);
	}
}
