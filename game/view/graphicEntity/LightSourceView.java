package view.graphicEntity;

import java.awt.Graphics;

import model.ILightSource;
import view.MyCanvas;
import view.ViewElement;
import view.Viewport;


public class LightSourceView extends ViewElement implements ILightSource {

	public int radius;
	private ILightSource source;


	public LightSourceView (ILightSource l) {
		super(0, 0, 1);
		this.source = l;
	}

	public void update (Viewport vp) {
		this.setPosition(vp.toLocalX(this.getPosX()), vp.toLocalY(this.getPosY()), vp.getScale(), (int) (vp.getScale() * this.getRadius() * MyCanvas.METRIC_BASE));
	}

	@Override
	public void setPosition (int x, int y, double scale) {
		this.x = x;
		this.y = y;
		this.scale = scale;
	}

	public void setPosition (int x, int y, double scale, int radius) {
		this.radius = radius;
		this.setPosition(x, y, scale);
	}

	@Override
	protected void paint (Graphics g) {}

	@Override
	public double getPosX () {
		return this.source.getPosX();
	}

	@Override
	public double getPosY () {
		return this.source.getPosY();
	}

	@Override
	public double getRadius () {
		return this.source.getRadius();
	}

}
