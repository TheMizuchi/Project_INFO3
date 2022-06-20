package view.animation;

import model.Model;
import model.entity.EntityProperties;
import view.animation.bank.AnimationBank;
import view.animation.bank.J2Bank;


public class J2Animation extends EntityAnimation {

	private J2Bank j2b;


	public J2Animation () {
		super(AnimationBank.getAnimationBank(EntityProperties.J2.getID()));
		this.j2b = (J2Bank) AnimationBank.getAnimationBank(EntityProperties.J2.getID());
	}

	public void attack () {
		m_sprite = this.j2b.attack;
		this.start();
	}

}
