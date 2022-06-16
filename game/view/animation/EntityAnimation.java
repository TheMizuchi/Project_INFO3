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
	protected int orientation;
	protected EntityAnimationBank ab;


	public EntityAnimation (EntityAnimationBank a) {
		super();
		this.ab = a;
		this.scale = 1F;
		this.h = this.ab.idle.m_height;
		this.w = this.ab.idle.m_width;
		this.orientation = 1;
		this.idle();
	}

	public void setPosition (int x, int y, double scale) {
		this.x = x;
		this.y = y;
		this.scale = scale;
	}

	@Override
	public void paint (Graphics g) {
		//if(m_sprite == null || m_fixImage == null) return;
		BufferedImage img = m_sprite.m_images[m_idx];
		g.drawImage(img, (int) (this.x - (this.w * this.scale / 2)), (int) (this.y - this.h * this.scale / 2), (int) (this.scale * img.getWidth() * this.orientation), (int) (this.scale * img.getHeight()), null);
	}

	public void turnLeft () {
		this.orientation = -1;
	}

	public void turnRight () {
		this.orientation = 1;
	}
	
	public void idle() {
		m_sprite = this.ab.idle;
		this.start();
	}

	public int getH () {
		return this.h;
	}

	public int getW () {
		return this.w;
	}

}
