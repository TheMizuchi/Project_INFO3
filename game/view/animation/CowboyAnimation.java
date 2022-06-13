package view.animation;

import view.EntityAnimation;
import view.animation.bank.CowboyBank;


public class CowboyAnimation extends EntityAnimation {

	CowboyBank cb;
	
	public CowboyAnimation (CowboyBank ab) {
		super(ab);
		this.cb = ab;
	}

	public void spin () {
		m_sprite = this.cb.spin;
		this.start();
	}

}
