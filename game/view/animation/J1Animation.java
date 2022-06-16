package view.animation;

import model.Model;
import view.animation.bank.AnimationBank;
import view.animation.bank.J1Bank;


public class J1Animation extends EntityAnimation {

	private J1Bank j1b;


	public J1Animation () {
		super(AnimationBank.getAnimationBank(Model.J1_ID));
		this.j1b = (J1Bank) AnimationBank.getAnimationBank(Model.J1_ID);
	}

	public void walk () { // Je rajoute walk ici, sinon je peux pas l'utiliser.
		m_sprite = this.j1b.walk;
		this.start();
	}

	public void attack () {
		m_sprite = this.j1b.attack;
		this.start();
	}

}
