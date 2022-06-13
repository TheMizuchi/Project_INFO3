package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class EntityAnimation extends Animation{
	protected int x, y;
	protected double scale;
	protected AnimationBank ab;
	
	public EntityAnimation(AnimationBank ab) {
		super();
		this.ab = ab;
		this.scale = 1F;
	}
	
	public void setPosition(int x, int y, double scale) {
		this.x = x;
		this.y = y;
		this.scale = scale;
	}
	
	@Override
	public void paint (Graphics g) {
		BufferedImage img;

		if (this.done()) {
			img = m_fixImage;
		} else {
			img = m_sprite.m_images[m_idx];
		}
		g.drawImage(img, (int) (this.x - (m_sprite.m_width * this.scale / 2)), (int) (this.y - m_sprite.m_height * this.scale / 2), (int) (this.scale * img.getWidth()), (int) (this.scale * img.getHeight()), null);
	}
	
	
	public void turnLeft() {
		m_sprite = ab.turnLeft;
		this.left();
		this.start();
	}
	
	public void turnRight() {
		m_sprite = ab.turnRight;
		this.right();
		this.start();
	}
	
	public void left() {
		m_fixImage = ab.left;
	}

	public void right() {
		m_fixImage = ab.right;
	}
	
}
