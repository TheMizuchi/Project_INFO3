package view.animation;

import model.Model;
import model.entity.EntityProperties;
import view.animation.bank.AnimationBank;
import view.animation.bank.SkeletonBank;


public class SkeletonAnimation extends EntityAnimation {

	private SkeletonBank zb;


	public SkeletonAnimation () {
		super(AnimationBank.getAnimationBank(EntityProperties.SKELETON.getID()));
		this.zb = (SkeletonBank) AnimationBank.getAnimationBank(EntityProperties.SKELETON.getID());
	}

	public void attack () {
		m_sprite = this.zb.attack;
		this.start();
	}

}
