package view.animation;

import model.Model;
import model.entity.EntityProperties;
import view.animation.bank.AnimationBank;
import view.animation.bank.CowboyBank;


public class CowboyAnimation extends EntityAnimation {

	private CowboyBank cb;


	public CowboyAnimation () {
		super(AnimationBank.getAnimationBank(EntityProperties.COWBOY.getID()));
		this.cb = (CowboyBank) AnimationBank.getAnimationBank(EntityProperties.COWBOY.getID());
	}

	public void spin () {
		m_sprite = this.cb.spin;
		this.start();
	}
}
