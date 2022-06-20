package view.animation;

import model.Model;
import model.entity.EntityProperties;
import view.animation.bank.AnimationBank;
import view.animation.bank.BloonBank;


public class BloonAnimation extends EntityAnimation {

	BloonBank bb;


	public BloonAnimation () {
		super(AnimationBank.getAnimationBank(EntityProperties.BLOON.getID()));
		this.bb = (BloonBank) AnimationBank.getAnimationBank(EntityProperties.BLOON.getID());
	}

	public void explode () {
		m_sprite = this.bb.explode;
		this.start();
	}
}
