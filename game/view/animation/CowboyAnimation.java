package view.animation;

import model.Model;
import view.animation.bank.AnimationBank;
import view.animation.bank.CowboyBank;


public class CowboyAnimation extends EntityAnimation {

	CowboyBank cb;


	public CowboyAnimation () {
		super();
		this.cb = (CowboyBank) AnimationBank.getAnimationBank(Model.COWBOY_ID);
	}

	public void spin () {
		m_sprite = this.cb.spin;
		this.start();
	}
}
