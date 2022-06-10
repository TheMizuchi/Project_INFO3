package view.graphicEntity;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

import view.Animation;
import view.Animation.AnimationListener;
import view.EntityView;
import view.Sprite;
import view.animation.CowboyAnimation;


public class CowboyView extends EntityView {

	Animation a;
	AnimationListener al;
	private Sprite spin;


	public CowboyView (int x, int y) {
		this.x = x;
		this.y = y;

		this.spin = Sprite.loadSprite("resources/winchester-4x6.png", 4, 6);

		this.al = new AnimationListener() {

			@Override
			public void done (Animation a) {
				a.restart();
			}
		};
	}

	public void spin () {
		a = new CowboyAnimation(this.spin, 20, this.al);
		a.setPosition(this.x, this.y, 5F);
	}

	@Override
	public void paint (Graphics g) {

		if (a == null || a.done()) {
			g.setColor(Color.BLUE);
			g.fillOval(this.x, this.y, 50, 25);
		} else {
			a.paint(g);
		}
	}

}
