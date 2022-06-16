package view.animation;

import model.Model;
import view.animation.bank.AnimationBank;
import view.animation.bank.J2Bank;


public class J2Animation extends EntityAnimation {

	private J2Bank j2b;


	public J2Animation () {
		super(AnimationBank.getAnimationBank(Model.J2_ID));
		this.j2b = (J2Bank) AnimationBank.getAnimationBank(Model.J2_ID);
	}

	public void attack () {
		m_sprite = this.j2b.attack;
		this.start();
	}

}
