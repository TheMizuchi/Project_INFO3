package view.animation;

import model.entity.EntityProperties;
import view.animation.bank.AnimationBank;
import view.animation.bank.DoorBank;

public class DoorAnimation extends EntityAnimation {
	DoorBank db;
	
	public DoorAnimation () {
		super(AnimationBank.getAnimationBank(EntityProperties.DOORC.getID()));
		this.db = (DoorBank) AnimationBank.getAnimationBank(EntityProperties.DOORC.getID());
	}

}
