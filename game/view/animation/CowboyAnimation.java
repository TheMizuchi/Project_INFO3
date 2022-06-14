package view.animation;

import view.animation.bank.AnimationBank;
import view.animation.bank.CowboyBank;


public class CowboyAnimation extends EntityAnimation {

	CowboyBank cb;
	
	public CowboyAnimation () {
		super();
		this.cb = (CowboyBank) AnimationBank.getAnimationBank(AnimationBank.COWBOYID);
	}

	public void spin () {
		m_sprite = this.cb.spin;
		this.start();
	}

}
