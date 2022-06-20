package view.animation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import model.entity.EntityProperties;
import view.animation.bank.AnimationBank;
import view.animation.bank.J1Bank;


public class J1Animation extends EntityAnimation {

	private J1Bank j1b;
	boolean show;


	public J1Animation () {
		super(AnimationBank.getAnimationBank(EntityProperties.J1.getID()));
		this.j1b = (J1Bank) AnimationBank.getAnimationBank(EntityProperties.J1.getID());
		this.show = true;
	}

	public void show () {
		this.show = true;
	}

	public void hide () {
		this.show = false;
	}

	public void attack () {
		m_sprite = this.j1b.attack;
		this.start();
	}

	@Override
	public void paint (Graphics g) {
		if (!show)
			return;
		BufferedImage img = m_sprite.m_images[m_idx];
		g.drawImage(img, (int) (this.x - (this.orientation * this.w * this.scale / 2)), (int) (this.y - this.h * this.scale * 3 / 4), (int) (this.scale * img.getWidth() * this.orientation), (int) (this.scale * img.getHeight()), null);
	}

}
