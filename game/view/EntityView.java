package view;

import java.awt.Graphics;

public abstract class EntityView extends ViewElement{
	protected int x, y, height, width;
	protected double scale;
	//protected Entity e;
	public abstract void paint(Graphics g);
	
}
