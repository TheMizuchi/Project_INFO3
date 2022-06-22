package view.animation;

import model.entity.EntityProperties;
import view.animation.bank.AnimationBank;
import view.animation.bank.BloonBank;


public class BloonAnimation extends EntityAnimation {

	BloonBank bb;
	Sprite idle;
	Sprite walk;
	Sprite explode;


	public BloonAnimation () {
		super(AnimationBank.getAnimationBank(EntityProperties.BLOON.getID()));
		this.bb = (BloonBank) AnimationBank.getAnimationBank(EntityProperties.BLOON.getID());
		setLevel(2);
	}

	public void explode () {
		m_sprite = this.explode;
		this.start();
	}

	public void setLevel (int n) {

		switch (n) {
			case 0:
				this.idle = this.bb.Ired;
				this.explode = this.bb.red;
				break;
			case 1:
				this.idle = this.bb.Iblue;
				this.explode = this.bb.blue;
				break;
			case 2:
				this.idle = this.bb.Igreen;
				this.explode = this.bb.green;
				break;
			case 3:
				this.idle = this.bb.Iyellow;
				this.explode = this.bb.yellow;
				break;
			case 4:
				this.idle = this.bb.Ipink;
				this.explode = this.bb.pink;
				break;
			case 5:
				this.idle = this.bb.Ipurple;
				this.explode = this.bb.purple;
				break;
			default:
				throw new RuntimeException("le niveau du ballon a rencontré un problème");
		}
		this.walk = this.idle;
		m_sprite = this.idle;
	}

	@Override
	public void idle () {
		m_sprite = this.idle;
		this.start();
	}

	@Override
	public void walk () {
		m_sprite = this.walk;
		this.start();
	}

	@Override
	public void setPosition (int x, int y, double scale) {
		this.x = x;
		this.y = y;
		this.scale = scale / 20;
	}
}
