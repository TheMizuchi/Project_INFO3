package view.animation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import model.entity.EntityProperties;
import view.animation.bank.AnimationBank;
import view.animation.bank.KeyBank;


public class KeyAnimation extends EntityAnimation {

	private KeyBank kb;
	boolean show;


	public KeyAnimation () {
		super(AnimationBank.getAnimationBank(EntityProperties.KEY.getID()));
		this.kb = (KeyBank) AnimationBank.getAnimationBank(EntityProperties.KEY.getID());
		this.show = true;
	}

	public void show () {
		this.show = true;
	}

	public void hide () {
		this.show = false;
	}

	@Override
	public void paint (Graphics g) {
		if (!show)
			return;
		BufferedImage img = m_sprite.m_images[m_idx];
		g.drawImage(img, (int) (this.x - (this.orientation * this.w * this.scale / 2)), (int) (this.y - this.h * this.scale * 3 / 4), (int) (this.scale * img.getWidth() * this.orientation), (int) (this.scale * img.getHeight()), null);
	}
}
