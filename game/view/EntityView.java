package view;

import java.awt.Graphics;

public abstract class EntityView extends ViewElement{
	
	public EntityView (int x, int y, double scale) {
		super(x, y, scale);
	}
	protected EntityInterface e;
	public abstract void paint(Graphics g);
	
}
