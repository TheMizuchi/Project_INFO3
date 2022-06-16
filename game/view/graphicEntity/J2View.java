package view.graphicEntity;

import java.awt.Graphics;

import model.entity.EntityInterface;
import view.MyCanvas;
import view.animation.Animation;
import view.animation.J2Animation;
import view.animation.Animation.AnimationListener;
import view.animation.bank.AnimationBank;
import view.animation.bank.J1Bank;


public class J2View extends EntityView {

	AnimationListener al;
	J2Animation a;


	public J2View (EntityInterface e) {
		super(0, 0, 1, e, new J2Animation());
		this.a = (J2Animation) super.a;
	}
	
	public void attack () {
		a.setListener(this.al);
		a.attack();
	}
}
