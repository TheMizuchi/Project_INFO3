package view.animation;

import model.entity.EntityProperties;
import view.animation.bank.AnimationBank;
import view.animation.bank.DogeBank;


public class DogeAnimation extends EntityAnimation {

	private DogeBank db;


	public DogeAnimation () {
		super(AnimationBank.getAnimationBank(EntityProperties.DOGE.getID()));
		this.db = (DogeBank) AnimationBank.getAnimationBank(EntityProperties.DOGE.getID());
	}
}
