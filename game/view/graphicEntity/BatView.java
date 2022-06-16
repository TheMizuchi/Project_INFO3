package view.graphicEntity;

import java.awt.Graphics;

import model.entity.EntityInterface;
import view.EntityView;
import view.animation.Animation;
import view.animation.BatAnimation;
import view.animation.Animation.AnimationListener;


public class BatView extends EntityView {

	AnimationListener al;
	BatAnimation a;


	public BatView (EntityInterface e) {
		super(0, 0, 1, e);
		this.a = new BatAnimation();
		a.setPosition(x, y, scale);
		this.al = new AnimationListener() {

			@Override
			public void done (Animation a) {

			}
		};
	}

	public void setPosition (int x, int y, double scale) {
		this.x = x;
		this.y = y;
		this.scale = scale;
		a.setPosition(x, y, scale);
	}

	public void setDelay (int delay) {
		a.setDelay(delay);
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
	public void turnLeft () {
		// TODO Auto-generated method stub

	}

	@Override
	public void turnRight () {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint (Graphics g) {
		this.a.paint(g);

	}

}
