package view.animation;

import model.Model;
import view.animation.bank.AnimationBank;
import view.animation.bank.BatBank;


public class BatAnimation extends EntityAnimation {

	private BatBank bb;


	public BatAnimation () {
		super(AnimationBank.getAnimationBank(Model.BAT_ID));
		this.bb = (BatBank) AnimationBank.getAnimationBank(Model.BAT_ID);
	}

	public void walk () {
		m_sprite = this.bb.walk;
		this.start();
	}
}
