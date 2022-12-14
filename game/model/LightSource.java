package model;

import model.entity.Entity;
import view.MyCanvas;
import view.graphicEntity.LightSourceView;


public class LightSource implements ILightSource {

	private double x, y;
	private double radius;
	private Entity entity;
	private LightSourceView sv;


	public LightSource (double x, double y, double radius, Entity e) {
		this(x, y, radius);
		this.entity = e;
	}

	public LightSource (double x, double y, double radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.sv = new LightSourceView(this);
		MyCanvas.getInstance().createLightSourceView(sv);
	}
	
	Entity getEntity() {
		return entity;
	}

	void setPosition (double x, double y, double radius) {
		setPosition(x, y);
		setRadius(radius);
	}

	void setPosition (double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void setRadius (double radius) {
		this.radius = radius;
	}

	void setEntity (Entity e) {
		this.entity = e;
	}

	public void update () {

		if (this.entity != null) {
			this.setPosition(this.entity.getPosX(), this.entity.getPosY());
		}
	}

	@Override
	public double getPosX () {
		return this.x;
	}

	@Override
	public double getPosY () {
		return this.y;
	}

	@Override
	public double getRadius () {
		return this.radius;
	}
}
