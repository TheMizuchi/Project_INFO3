package view.animation;

import model.Model;
import view.animation.bank.AnimationBank;
import view.animation.bank.CowboyBank;
import view.animation.bank.TorchBank;

public class TorchAnimation extends EntityAnimation{
	private TorchBank cb;


	public TorchAnimation () {
		super(AnimationBank.getAnimationBank(Model.TORCH_ID));
		this.cb = (TorchBank) AnimationBank.getAnimationBank(Model.TORCH_ID);
	}
	
}
