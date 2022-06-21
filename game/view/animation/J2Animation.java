package view.animation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import model.entity.EntityProperties;
import view.animation.bank.AnimationBank;
import view.animation.bank.J2Bank;


public class J2Animation extends EntityAnimation {

	private J2Bank j2b;
	boolean show;


	public J2Animation () {
		super(AnimationBank.getAnimationBank(EntityProperties.J2.getID()));
		this.j2b = (J2Bank) AnimationBank.getAnimationBank(EntityProperties.J2.getID());
		this.show = true;
	}

	public void show () {
		this.show = true;
	}

	public void hide () {
		this.show = false;
	}

	public void attack () {
		m_sprite = this.j2b.attack;
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
