package view.animation;

import model.entity.EntityProperties;
import view.animation.bank.AnimationBank;
import view.animation.bank.ArrowBank;


public class ArrowAnimation extends EntityAnimation {

	private ArrowBank ab;


	public ArrowAnimation () {
		super(AnimationBank.getAnimationBank(EntityProperties.ARROW.getID())); //entité arrow;
		this.ab = (ArrowBank) AnimationBank.getAnimationBank(EntityProperties.ARROW.getID());
	}

	public void walk () {
		m_sprite = this.ab.walk;
		this.start();
	}

	public void hautgauche () {
		m_sprite = this.ab.hautgauche;
		this.start();
	}

	public void haut () {
		m_sprite = this.ab.haut;
		this.start();
	}

	public void hautdroite () {
		m_sprite = this.ab.hautdroite;
		this.start();
	}

	public void basgauche () {
		m_sprite = this.ab.basgauche;
		this.start();
	}

	public void bas () {
		m_sprite = this.ab.bas;
		this.start();
	}

	public void basdroite () {
		m_sprite = this.ab.basdroite;
		this.start();
	}

	@Override
	public void attack () {}
}
