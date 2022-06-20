package view.animation;

import model.Model;
import view.animation.bank.AnimationBank;
import view.animation.bank.MysteryBank;


public class MysteryAnimation extends EntityAnimation {

	private MysteryBank mb;


	public MysteryAnimation () {
		super(AnimationBank.getAnimationBank(Model.DOGE_ID));
		this.mb = (MysteryBank) AnimationBank.getAnimationBank(Model.MYSTERY_ID);
	}
}
