package view;

import java.awt.Graphics;


public abstract class ViewElement {

	protected int x;
	protected int y;
	protected double scale;


	public ViewElement (int x, int y, double scale) {
		this.x = x;
		this.y = y;
		this.scale = scale;
	}

	protected abstract void paint (Graphics g);

	protected abstract void setPosition (int x, int y, double scale);
}
