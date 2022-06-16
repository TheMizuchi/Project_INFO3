package view.animation;

import model.Model;
import view.animation.bank.AnimationBank;
import view.animation.bank.BalloonBank;


public class BalloonAnimation extends EntityAnimation {

	BalloonBank bb;


	public BalloonAnimation () {
		super(AnimationBank.getAnimationBank(Model.BLOON_ID));
		this.bb = (BalloonBank) AnimationBank.getAnimationBank(Model.BLOON_ID);
	}

	public void explode () {
		m_sprite = this.bb.explode;
		this.start();
	}
}
