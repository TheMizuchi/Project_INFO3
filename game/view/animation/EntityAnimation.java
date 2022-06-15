package view.animation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import model.Model;
import view.animation.bank.AnimationBank;
import view.animation.bank.EntityAnimationBank;


public abstract class EntityAnimation extends Animation {

	protected int x, y;
	protected int h, w;
	protected double scale;
	protected EntityAnimationBank ab;


	public EntityAnimation () {
		super();
		this.ab = AnimationBank.getAnimationBank(Model.COWBOY_ID);
		this.scale = 1F;
		this.h = this.ab.left.getHeight();
		this.w = this.ab.left.getWidth();
		this.left();
	}

	public void setPosition (int x, int y, double scale) {
		this.x = x;
		this.y = y;
		this.scale = scale;
	}

	@Override
	public void paint (Graphics g) {
		//if(m_sprite == null || m_fixImage == null) return;
		BufferedImage img;

		if (this.done()) {
			img = m_fixImage;
		} else {
			img = m_sprite.m_images[m_idx];
		}
		g.drawImage(img, (int) (this.x - (this.w * this.scale / 2)), (int) (this.y - this.h * this.scale / 2), (int) (this.scale * img.getWidth()), (int) (this.scale * img.getHeight()), null);
	}

	public void turnLeft () {
		m_sprite = ab.turnLeft;
		this.left();
		this.start();
	}

	public void turnRight () {
		m_sprite = ab.turnRight;
		this.right();
		this.start();
	}

	public void left () {
		m_fixImage = ab.left;
	}

	public void right () {
		m_fixImage = ab.right;
	}

	public int getH () {
		return this.h;
	}

	public int getW () {
		return this.w;
	}

}
