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


	public Animation (int delay, AnimationListener al) {
		this.al = al;
		this.delay = delay;
		this._al = new _AnimationListener();
	}

	public abstract void setPosition (int x, int y, float scale);

	//Passe à l'image suivante de l'animation et renvoie true si réussi, false sinon
	public abstract boolean nextImage ();

	//Renvoie True si l'animation est terminé
	public abstract boolean done ();
	
	public abstract void restart();
	
	public abstract void paint(Graphics g);


	protected class _AnimationListener implements TimerListener {

		MyTimer t;


		public _AnimationListener () {
			t = MyTimer.getTimer();
			t.setTimer(0, this);
		}
		
		public void restart() {
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
