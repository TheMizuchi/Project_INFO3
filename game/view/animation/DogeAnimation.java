package view.animation;

import model.entity.EntityProperties;
import view.animation.bank.AnimationBank;
import view.animation.bank.DogeBank;


public class DogeAnimation extends EntityAnimation {

	private DogeBank db;
	private Sprite m_s;


	public DogeAnimation () {
		super(AnimationBank.getAnimationBank(EntityProperties.DOGE.getID()));
		this.db = (DogeBank) AnimationBank.getAnimationBank(EntityProperties.DOGE.getID());
		m_s = this.db.idle;
	}

	@Override
	public void idle () {
		m_sprite = (m_s != null) ? m_s : this.ab.idle;
		this.h = m_sprite.m_height;
		this.w = m_sprite.m_width;
		this.start();
	}

	@Override
	public void walk () {
		m_sprite = (m_s != null) ? m_s : this.ab.idle;
		this.h = m_sprite.m_height;
		this.w = m_sprite.m_width;
		this.start();
	}

	public void angry () {
		m_s = this.db.angry;
		this.idle();
	}
}
