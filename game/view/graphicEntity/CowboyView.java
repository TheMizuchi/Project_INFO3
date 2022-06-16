package view.graphicEntity;

import java.awt.Graphics;

import model.entity.EntityInterface;
import view.EntityView;
import view.MyCanvas;
import view.Viewport;
import view.animation.Animation;
import view.animation.CowboyAnimation;
import view.animation.Animation.AnimationListener;
import view.animation.bank.AnimationBank;
import view.animation.bank.CowboyBank;

/*
 * Cette classe sert à définir le visuel de l'entité. Elle doit définir quelles
 * animations ou quelles images fixes afficher mais ne s'occupe pas de les
 * afficher elle même C'est le rôle de la classe [EntityName]Animation
 */


public class CowboyView extends EntityView {

	AnimationListener al;
	CowboyAnimation a;
	boolean left;


	public CowboyView (EntityInterface e) {
		super(0, 0, 1, e);
		this.left = false;
		this.a = new CowboyAnimation();
		a.setPosition(x, y, scale);
		this.al = new AnimationListener() {

			@Override
			public void done (Animation a) {
				idle();
			}
		};
	}
	
	@Override
	public void update(Viewport vp) {
		this.setPosition(vp.toLocalX(this.entity.getPosX()), vp.toLocalY(this.entity.getPosY()), vp.getScale());
	}

	protected void idle () {
		this.a.idle();
	}

	@Override
	public void setPosition (int x, int y, double scale) {
		this.x = x;
		this.y = y;
		this.scale = scale;
		a.setPosition(x, y, scale);
	}

	public void setDelay (int delay) {
		a.setDelay(delay);
	}

	public void spin () {
		this.a.setListener(this.al);
		this.a.spin();
	}

	@Override
	public void turnLeft () {

		this.a.turnLeft();
	}

	@Override
	public void turnRight () {

		this.a.turnRight();
	}

	@Override
	public void paint (Graphics g) {
		this.a.paint(g);
	}

	@Override
	public int getH () {
		return this.a.getH();
	}

	@Override
	public int getW () {
		return this.a.getW();
	}

}
