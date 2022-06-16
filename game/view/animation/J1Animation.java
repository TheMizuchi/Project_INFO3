package view.animation;

import model.Model;
import view.animation.bank.AnimationBank;
import view.animation.bank.J1Bank;


public class J1Animation extends EntityAnimation {

	private J1Bank j1b;


	public J1Animation () {
		super(AnimationBank.getAnimationBank(Model.J2_ID));
		this.j1b = (J1Bank) AnimationBank.getAnimationBank(Model.J1_ID);
	}

	public void attack () {
		m_sprite = this.j1b.attack;
		this.start();
	}

}
