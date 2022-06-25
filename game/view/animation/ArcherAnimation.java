package view.animation;

import model.entity.EntityProperties;
import view.animation.bank.AnimationBank;
import view.animation.bank.ArcherBank;


public class ArcherAnimation extends EntityAnimation {

	private ArcherBank cb;


	public ArcherAnimation () {
		super(AnimationBank.getAnimationBank(EntityProperties.ARCHER.getID()));
		this.cb = (ArcherBank) AnimationBank.getAnimationBank(EntityProperties.ARCHER.getID());
		this.delay = 50;
	}

	@Override
	public void setPosition (int x, int y, double scale) {
		this.x = x;
		this.y = y;
		this.scale = scale / 30;
	}

	@Override
	public void attack () {}
}
