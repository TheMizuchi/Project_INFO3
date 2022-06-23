package view.animation;

import model.Model;
import model.entity.EntityProperties;
import view.animation.bank.AnimationBank;
import view.animation.bank.StairBank;


public class StairAnimation extends EntityAnimation {

	private StairBank sb;


	public StairAnimation () {
		super(AnimationBank.getAnimationBank(EntityProperties.STAIRS.getID()));
		this.sb = (StairBank) AnimationBank.getAnimationBank(EntityProperties.STAIRS.getID());
	}

}
