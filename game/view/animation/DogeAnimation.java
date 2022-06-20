package view.animation;

import model.Model;
import view.animation.bank.AnimationBank;
import view.animation.bank.CowboyBank;
import view.animation.bank.DogeBank;


public class DogeAnimation extends EntityAnimation {

	private DogeBank db;


	public DogeAnimation () {
		super(AnimationBank.getAnimationBank(Model.DOGE_ID));
		this.db = (DogeBank) AnimationBank.getAnimationBank(Model.DOGE_ID);
	}
}
