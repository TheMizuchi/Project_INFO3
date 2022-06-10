package view.animation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import view.Animation;
import view.Sprite;


public class CowboyAnimation extends Animation {

	int m_x, m_y;
	int m_idx;
	float m_scale;
	Sprite m_sprite;
	boolean m_done;


	public CowboyAnimation (Sprite s, int delay, AnimationListener al) {
		super(delay, al);
		m_x = 0;
		m_y = 0;
		m_idx = 0;
		m_scale = 1F;
		m_sprite = s;
		m_done = false;

	}

	@Override
	public void setPosition (int x, int y, float scale) {
		m_x = x;
		m_y = y;
		m_scale = scale;

	}
	
	public void restart() {
		m_idx = 0;
		this._al.restart();
	}
	@Override
	public boolean nextImage () {

		if (m_idx < m_sprite.m_images.length - 1) {
			m_idx++;
			return m_idx < m_sprite.m_images.length - 1;
		} else {
			m_done = true;
			return false;
		}
	}

	@Override
	public boolean done () {
		return m_done;
	}

	@Override
	public void paint (Graphics g) {
		BufferedImage img = m_sprite.m_images[m_idx];
		g.drawImage(img, m_x, m_y, (int) (m_scale * img.getWidth()), (int) (m_scale * img.getHeight()), null);
	}

}
