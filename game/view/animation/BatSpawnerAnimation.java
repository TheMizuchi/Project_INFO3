package view.animation;

import model.entity.EntityProperties;
import view.animation.bank.AnimationBank;
import view.animation.bank.BatSpawnerBank;


public class BatSpawnerAnimation extends EntityAnimation {

	private BatSpawnerBank bsb;


	public BatSpawnerAnimation () {
		super(AnimationBank.getAnimationBank(EntityProperties.BATSPAWNER.getID()));
		this.bsb = (BatSpawnerBank) AnimationBank.getAnimationBank(EntityProperties.BATSPAWNER.getID());
	}

	@Override
	public void attack () {}
}
