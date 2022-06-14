package view.animation;
import view.animation.bank.AnimationBank;
import view.animation.bank.BalloonBank;


public class BalloonAnimation extends EntityAnimation {
	
	BalloonBank bb;

	public BalloonAnimation() {
		super();
		this.bb = (BalloonBank) AnimationBank.getAnimationBank(AnimationBank.BLOONID);
	}
	
	public void explode() {
		m_sprite = this.bb.explode;
		this.start();
	}
}
