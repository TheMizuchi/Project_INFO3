package view.animation;

import model.Model;
import view.animation.bank.AnimationBank;
import view.animation.bank.DogeBank;


public class DogeAnimation extends EntityAnimation {

	private DogeBank db;


	public DogeAnimation () {
		super(AnimationBank.getAnimationBank(Model.DOGE_ID));
		this.db = (DogeBank) AnimationBank.getAnimationBank(Model.DOGE_ID);
	}

	@Override
	public void idle () {
		m_sprite = this.ab.idle;
		this.h = m_sprite.m_height;
		this.w = m_sprite.m_width;
		this.start();
	}

	@Override
	public void walk () {
		m_sprite = this.ab.walk;
		this.h = m_sprite.m_height;
		this.w = m_sprite.m_width;
		this.start();
	}

	public void angry () {
		m_sprite = this.db.angry;
		this.h = m_sprite.m_height;
		this.w = m_sprite.m_width;
		this.start();
	}
}
