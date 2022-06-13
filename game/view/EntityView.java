package view;

import java.awt.Graphics;


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

	public abstract void paint (Graphics g);

}
