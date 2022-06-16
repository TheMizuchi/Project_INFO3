package view.graphicEntity;

import java.awt.Graphics;

import model.entity.EntityInterface;
import view.EntityView;
import view.MyCanvas;
import view.Viewport;
import view.animation.Animation;
import view.animation.J1Animation;
import view.animation.Animation.AnimationListener;
import view.animation.bank.AnimationBank;
import view.animation.bank.J1Bank;


public class J1View extends EntityView {

	AnimationListener al;
	J1Animation a;
	


	public J1View (EntityInterface e) {
		super(0, 0, 1, e);
		this.a = new J1Animation();
		a.setPosition(x, y, scale);
		this.al = new AnimationListener() {

			@Override
			public void done (Animation a) {
				idle();

			}
		};
//		a.setListener(al);
//		a.setDelay(300);
//		a.walk();
	}

	protected void idle () {
		this.a.idle();
	}
	
//	protected void walk() {
//		this.a.walk();
//	}

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

	public void setDelay (int delay) {
		a.setDelay(delay);
	}

	public void attack () {
		a.setListener(this.al);
		a.attack();
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
		a.paint(g);

	}

}
