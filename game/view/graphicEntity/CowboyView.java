package view.graphicEntity;

import java.awt.Color;
import java.awt.Graphics;
import view.Animation;
import view.Animation.AnimationListener;
import view.EntityView;
import view.animation.CowboyAnimation;

/*
 * Cette classe sert à définir le visuel de l'entité. Elle doit définir quelles
 * animations ou quelles images fixes afficher mais ne s'occupe pas de les
 * afficher elle même C'est le rôle de la classe [EntityName]Animation
 */


public class CowboyView extends EntityView {

	AnimationListener al;
	CowboyAnimation a;
	boolean left;


	public CowboyView (int x, int y, double scale) {
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.left = false;
		this.a = new CowboyAnimation();
		this.al = new AnimationListener() {

			@Override
			public void done (Animation a) {

			}
		};
	}
	
	public void setPosition(int x, int y, float scale) {
		a.setPosition(x, y, scale);
	}
	public void setDelay(int delay) {
		a.setDelay(delay);
	}
	

	public void spin () {
		a.setPosition((int) (this.x), (int) (this.y), (float) this.scale);
		a.setListener(new AnimationListener() {

			@Override
			public void done (Animation a) {
				CowboyView.this.a.start();
			}
		});
		a.spin();
	}

	public void turnLeft () {

		if (!this.left) {
			this.left = true;
			a.setPosition((int) (this.x), (int) (this.y), (float) this.scale);
			a.setListener(new AnimationListener() {

				@Override
				public void done (Animation a) {
					CowboyView.this.turnRight();
				}
			});
			a.turnLeft();
		}
	}

	public void turnRight () {

		if (this.left) {
			this.left = false;
			a.setPosition((int) (this.x), (int) (this.y), (float) this.scale);
			a.setListener(new AnimationListener() {

				@Override
				public void done (Animation a) {
					CowboyView.this.turnLeft();
				}
			});
			a.turnRight();
		}
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
