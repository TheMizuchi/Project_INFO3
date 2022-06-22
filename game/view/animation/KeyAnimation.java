package view.animation;

import model.entity.EntityProperties;
import view.animation.bank.AnimationBank;
import view.animation.bank.TorchBank;


public class KeyAnimation extends EntityAnimation {

	private TorchBank kb;


	public KeyAnimation () {
		super(AnimationBank.getAnimationBank(EntityProperties.KEY.getID()));
		this.kb = (TorchBank) AnimationBank.getAnimationBank(EntityProperties.KEY.getID());
	}

}
