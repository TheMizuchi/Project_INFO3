package view.animation;

import model.Model;
import view.animation.bank.AnimationBank;
import view.animation.bank.CowboyBank;


public class CowboyAnimation extends EntityAnimation {

	private CowboyBank cb;


	public CowboyAnimation () {
		super(AnimationBank.getAnimationBank(Model.COWBOY_ID));
		this.cb = (CowboyBank) AnimationBank.getAnimationBank(Model.COWBOY_ID);
	}

	public void spin () {
		m_sprite = this.cb.spin;
		this.start();
	}
}
