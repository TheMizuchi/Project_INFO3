package view.animation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import view.animation.bank.EntityAnimationBank;


public abstract class EntityAnimation extends Animation {

	protected int x, y;
	protected int h, w;
	protected double scale;
	protected int orientation;
	protected EntityAnimationBank eab;


	public EntityAnimation (EntityAnimationBank a) {
		super();
		this.eab = a;
		this.scale = 1F;
		this.h = this.eab.idle.m_height;
		this.w = this.eab.idle.m_width;
		this.orientation = 1;
		this.al = new AnimationListener() {

			@Override
			public void done (Animation a) {
				idle();
			}

		};
		idle();
	}

	public void setPosition (int x, int y, double scale) {
		this.x = x;
		this.y = y;
		this.scale = scale;
	}

	@Override
	public void paint (Graphics g) {
		BufferedImage img = m_sprite.m_images[m_idx];
		g.drawImage(img, (int) (this.x - (this.orientation * this.w * this.scale / 2)), (int) (this.y - this.h * this.scale * 3 / 4), (int) (this.scale * img.getWidth() * this.orientation), (int) (this.scale * img.getHeight()), null);
	}

	public void setOrientation (int o) {
		this.orientation = o;
	}

	public void idle () {
		m_sprite = this.eab.idle;
		this.start();
	}

	public void walk () {
		m_sprite = this.eab.walk;
		this.start();
	}

	public void repeat () {
		this.setListener(new AnimationListener() {

			@Override
			public void done (Animation a) {
				start();
			}

		});
	}

	public void interrupt () {
		this.setListener(new AnimationListener() {

			@Override
			public void done (Animation a) {
				idle();
			}

		});
		this.stop();
	}

	public int getH () {
		return this.h;
	}

	public int getW () {
		return this.w;
	}

	public abstract void attack ();

}
