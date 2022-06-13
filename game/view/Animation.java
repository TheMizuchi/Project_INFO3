package view;

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
	BufferedImage[] m_images;
	int delay;

	protected int m_idx;
	protected Sprite m_sprite;
	protected BufferedImage m_fixImage;
	protected boolean m_done;


	public Animation () {
		m_idx = 0;
		m_done = false;
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
	public boolean nextImage () {

		if (m_idx < m_sprite.m_images.length - 1) {
			m_idx++;
			return true;
		} else {
			m_done = true;
			return false;
		}
	}

	//Renvoie True si l'animation est terminé
	public boolean done () {
		return m_done;
	}

	public void start () {
		m_idx = 0;
		m_done = false;
		this._al.start();
	}

	public abstract void paint (Graphics g);


	protected class _AnimationListener implements TimerListener {

		MyTimer t;


		public _AnimationListener () {
			t = MyTimer.getTimer();
		}

		public void start () {
			t.setTimer(0, this);
		}

		@Override
		public void expired () {

			if (nextImage()) {
				t.setTimer(delay, this);
			} else {
				if (al != null)
					al.done(Animation.this);
			}
		}
	}
}
