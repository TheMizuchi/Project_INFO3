package view;

import java.awt.Graphics;

import model.entity.EntityInterface;


public abstract class EntityView extends ViewElement {

	public EntityView (int x, int y, double scale, EntityInterface e) {
		super(x, y, scale);
		this.entity = e;
	}


	protected EntityInterface entity;


	@Override
	protected void setPosition (int x, int y, double scale) {
		this.x = x;
		this.y = y;
		this.scale = scale;
	}
	
	public void update(Viewport vp) {
		this.setPosition(vp.toLocalX(this.entity.getPosX()), vp.toLocalY(this.entity.getPosY()), vp.getScale());
	}
	
	public abstract int getH ();
	public abstract int getW ();

	public abstract void turnLeft ();
	public abstract void turnRight ();

	public abstract void paint (Graphics g);

}
