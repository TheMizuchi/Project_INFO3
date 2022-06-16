package view.animation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import common.MyTimer;
import common.TimerListener;


public abstract class Animation {

	//Permet de notifier si lanimation est terminé 
	public interface AnimationListener {

		void done (Animation a);
	}


	AnimationListener al;
	protected _AnimationListener _al;
	protected BufferedImage[] m_images;
	protected int delay;

	protected int m_idx;
	protected Sprite m_sprite;
	protected BufferedImage m_fixImage;
	protected boolean m_done;


	protected Animation () {
		m_idx = 0;
		m_done = true;
		this.delay = 20;
		this._al = new _AnimationListener();
	}

	public void setDelay (int delay) {
		this.delay = delay;
	}

	public void setListener (AnimationListener al) {
		this.al = al;
	}

	//Passe à l'image suivante de l'animation et renvoie true si réussi, false sinon
	private boolean nextImage () {

		if (m_idx < m_sprite.m_images.length - 1) {
			m_idx++;
			return true;
		} else {
			m_done = true;
			return false;
		}
	}

	//Renvoie True si l'animation est terminé
	protected boolean done () {
		return m_done;
	}

	protected void start () {
		m_idx = 0;
		m_done = false;
		this._al.start();
	}

	public abstract void paint (Graphics g);


	private class _AnimationListener implements TimerListener {

		MyTimer t;
		long elapsed;
		long last;


		public _AnimationListener () {
			this.t = MyTimer.getTimer();
		}

		public void start () {
			this.last = System.currentTimeMillis();
			this.t.setTimer(20, this);

		}

		@Override
		public void expired () {
			long now = System.currentTimeMillis();
			this.elapsed += now - this.last;
			this.last = now;

			while (this.elapsed > delay) {

				if (!nextImage()) {
					if (al != null)
						al.done(Animation.this);
				}
				this.elapsed -= delay;
			}

			if (!m_done) {
				this.t.setTimer(delay, this);
			}
		}
	}
}
