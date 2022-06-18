package view.animation;

import model.Model;
import view.animation.bank.AnimationBank;
import view.animation.bank.SkeletonBank;


public class SkeletonAnimation extends EntityAnimation {

	private SkeletonBank zb;


	public SkeletonAnimation () {
		super(AnimationBank.getAnimationBank(Model.SKELETON_ID));
		this.zb = (SkeletonBank) AnimationBank.getAnimationBank(Model.SKELETON_ID);
	}

	public void attack () {
		m_sprite = this.zb.attack;
		this.start();
	}

}
