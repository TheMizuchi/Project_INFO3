package view.graphicEntity;

import java.awt.Graphics;

import model.entity.EntityInterface;
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
		super(0, 0, 1, e, new BloonAnimation());
		this.a = (BloonAnimation) super.a;
	}

	public void explode () {
		this.a.explode();
	}
}
