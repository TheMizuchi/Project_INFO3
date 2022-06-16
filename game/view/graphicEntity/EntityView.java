package view.graphicEntity;

import java.awt.Graphics;

import model.entity.EntityInterface;
import view.ViewElement;
import view.Viewport;
import view.animation.Animation;
import view.animation.J1Animation;
import view.animation.Animation.AnimationListener;
import view.animation.EntityAnimation;


public abstract class EntityView extends ViewElement {

	public EntityView (int x, int y, double scale, EntityInterface e, EntityAnimation a) {
		super(x, y, scale);
		this.a = a;
		this.entity = e;
		this.al = new AnimationListener() {

			@Override
			public void done (Animation a) {
				idle();
			}
		};
		this.setPosition(x, y, scale);
	}

	protected AnimationListener al;
	protected EntityInterface entity;
	protected int orientation;
	protected EntityAnimation a;

	@Override
	protected void setPosition (int x, int y, double scale) {
		super.setPosition(x, y, scale);
		a.setPosition(x, y, scale);
	}
	
	public void setDelay (int delay) {
		a.setDelay(delay);
	}
	
	protected void idle () {
		this.a.idle();
	}

	public void update (Viewport vp) {
		this.setPosition(vp.toLocalX(this.entity.getPosX()), vp.toLocalY(this.entity.getPosY()), vp.getScale());
	}

	public int getH () {
		return this.a.getH();
	}
	
	public int getW () {
		return this.a.getW();
	}

	public void turnLeft () {
		this.orientation = -1;
	}

	public void turnRight () {
		this.orientation = 1;
	}

	public void paint (Graphics g) {
		a.paint(g);

	}

}
