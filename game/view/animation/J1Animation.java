package view.animation;

import model.Model;
import model.entity.EntityProperties;
import view.animation.bank.AnimationBank;
import view.animation.bank.J1Bank;


public class J1Animation extends EntityAnimation {

	private J1Bank j1b;


	public J1Animation () {
		super(AnimationBank.getAnimationBank(EntityProperties.J1.getID()));
		this.j1b = (J1Bank) AnimationBank.getAnimationBank(EntityProperties.J1.getID());
	}

	public void attack () {
		m_sprite = this.j1b.attack;
		this.start();
	}

}
