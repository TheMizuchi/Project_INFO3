package view.animation;

import model.Model;
import view.animation.bank.AnimationBank;
import view.animation.bank.BloonBank;


public class BloonAnimation extends EntityAnimation {

	BloonBank bb;


	public BloonAnimation () {
		super(AnimationBank.getAnimationBank(Model.BLOON_ID));
		this.bb = (BloonBank) AnimationBank.getAnimationBank(Model.BLOON_ID);
	}

	public void explode () {
		m_sprite = this.bb.explode;
		this.start();
	}
}
