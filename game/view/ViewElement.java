package view;

import java.awt.Graphics;

public abstract class ViewElement {
	protected int x;
	protected int y;
	protected double scale;
	
	public ViewElement(int x, int y, double scale) {
		this.x = x;
		this.y = y;
		this.scale = scale*MyCanvas.c_scale;
	}
	
	abstract void paint(Graphics g);
}
