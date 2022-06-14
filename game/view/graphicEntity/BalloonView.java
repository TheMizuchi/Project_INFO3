package view.graphicEntity;

import java.awt.Graphics;

import view.Animation;
import view.EntityInterface;
import view.EntityView;
import view.Animation.AnimationListener;

public class BalloonView extends EntityView {
	AnimationListener al;
	BalloonAnimation a;

	public BalloonView (int x, int y, double scale, EntityInterface e, BalloonBank bb) {
		super(x, y, scale, e);
		this.x = x;
		this.y = y;
		this.a = new BalloonAnimation(bb);
		a.setPosition(x, y, scale);
		this.al = new AnimationListener() {

			@Override
			public void done (Animation a) {

			}
		};
		
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

}
