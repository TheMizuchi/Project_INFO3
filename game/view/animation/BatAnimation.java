package view.animation;

import model.entity.EntityProperties;
import view.animation.bank.AnimationBank;
import view.animation.bank.BatBank;


public class BatAnimation extends EntityAnimation {

	private BatBank bb;


	public BatAnimation () {
		super(AnimationBank.getAnimationBank(EntityProperties.BAT.getID()));
		this.bb = (BatBank) AnimationBank.getAnimationBank(EntityProperties.BAT.getID());
	}

	public void walk () {
		m_sprite = this.bb.walk;
		this.start();
	}

	@Override
	public void attack () {}
}
