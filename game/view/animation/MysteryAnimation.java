package view.animation;

import model.entity.EntityProperties;
import view.animation.bank.AnimationBank;
import view.animation.bank.MysteryBank;


public class MysteryAnimation extends EntityAnimation {

	private MysteryBank mb;


	public MysteryAnimation () {
		super(AnimationBank.getAnimationBank(EntityProperties.MYSTERY.getID()));
		this.mb = (MysteryBank) AnimationBank.getAnimationBank(EntityProperties.MYSTERY.getID());
	}

	@Override
	public void setPosition (int x, int y, double scale) {
		this.x = x;
		this.y = y;
		this.scale = scale / 6;
	}
}
