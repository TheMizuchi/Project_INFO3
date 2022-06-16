package view.graphicEntity;

import java.awt.Graphics;

import model.entity.EntityInterface;
import view.EntityView;
import view.MyCanvas;
import view.Viewport;
import view.animation.Animation;
import view.animation.BloonAnimation;
import view.animation.Animation.AnimationListener;
import view.animation.bank.AnimationBank;
import view.animation.bank.BloonBank;


public class BloonView extends EntityView {

	AnimationListener al;
	BloonAnimation a;


	public BloonView (EntityInterface e) {
		super(0, 0, 1, e);
		this.a = new BloonAnimation();
		a.setPosition(x, y, scale);
		this.al = new AnimationListener() {

			@Override
			public void done (Animation a) {

			}
		};

	}

	public void explode () {
		this.a.explode();
	}

	@Override
	public void update (Viewport vp) {
		this.setPosition(vp.toLocalX(this.entity.getPosX()), vp.toLocalY(this.entity.getPosY()), vp.getScale());
	}

	@Override
	public void setPosition (int x, int y, double scale) {
		this.x = x;
		this.y = y;
		this.scale = scale;
		a.setPosition(x, y, scale);
	}

	@Override
	public void paint (Graphics g) {
		a.paint(g);

	}

	@Override
	public int getH () {
		return this.a.getH();
	}

	@Override
	public int getW () {
		return this.a.getW();
	}

	@Override
	public void turnLeft () {}

	@Override
	public void turnRight () {}

}
