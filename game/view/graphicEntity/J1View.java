package view.graphicEntity;

import java.awt.Graphics;

import model.entity.EntityInterface;
import view.MyCanvas;
import view.Viewport;
import view.animation.Animation;
import view.animation.J1Animation;
import view.animation.Animation.AnimationListener;
import view.animation.bank.AnimationBank;
import view.animation.bank.J1Bank;


public class J1View extends EntityView {

	
	J1Animation a;
	

	public J1View (EntityInterface e) {
		super(0, 0, 1, e, new J1Animation());
		this.a = (J1Animation) super.a;
		this.al = new AnimationListener() {

			@Override
			public void done (Animation a) {
				walk();

			}
		};
		a.setListener(al);
		a.setDelay(150);
		a.walk();
	}

	public void attack () {
		a.setListener(this.al);
		a.attack();
	}
}
