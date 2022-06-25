package view.animation;

import model.entity.EntityProperties;
import view.animation.bank.AnimationBank;
import view.animation.bank.StairsBank;


public class StairsAnimation extends EntityAnimation {

	private StairsBank sb;


	public StairsAnimation () {
		super(AnimationBank.getAnimationBank(EntityProperties.STAIRS.getID()));
		this.sb = (StairsBank) AnimationBank.getAnimationBank(EntityProperties.STAIRS.getID());
	}

	@Override
	public void attack () {}

}
