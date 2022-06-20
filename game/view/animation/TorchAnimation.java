package view.animation;

import model.Model;
import model.entity.EntityProperties;
import view.animation.bank.AnimationBank;
import view.animation.bank.CowboyBank;
import view.animation.bank.TorchBank;


public class TorchAnimation extends EntityAnimation {

	private TorchBank cb;


	public TorchAnimation () {
		super(AnimationBank.getAnimationBank(EntityProperties.TORCH.getID()));
		this.cb = (TorchBank) AnimationBank.getAnimationBank(EntityProperties.TORCH.getID());
	}

}
