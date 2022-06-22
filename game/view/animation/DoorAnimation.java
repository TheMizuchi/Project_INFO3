package view.animation;

import model.entity.EntityProperties;
import view.animation.bank.AnimationBank;
import view.animation.bank.DoorBank;


public class DoorAnimation extends EntityAnimation {

	DoorBank db;


	public DoorAnimation () {
		super(AnimationBank.getAnimationBank(EntityProperties.DOOR.getID()));
		this.db = (DoorBank) AnimationBank.getAnimationBank(EntityProperties.DOOR.getID());
	}

	public void opening () {
		m_sprite = this.db.opening;
		this.h = m_sprite.m_height;
		this.w = m_sprite.m_width;
		this.start();
	}

}
