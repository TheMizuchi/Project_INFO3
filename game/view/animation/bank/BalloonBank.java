package view.animation.bank;

import view.animation.Sprite;


public class BalloonBank extends EntityAnimationBank {

	public Sprite explode;


	private BalloonBank () {
		loadSpecificAnimation();
		loadBasicAnimation();
	}


	private static BalloonBank INSTANCE = null;


	public static BalloonBank getInstance () {

		if (INSTANCE == null) {
			INSTANCE = new BalloonBank();
		}
		return INSTANCE;
	}

	@Override
	protected void loadBasicAnimation () {
		BufferedImage[] basic = new BuffuredImage[1];
		
		this.left = this.turnLeft.m_images[0];
		this.right = this.turnRight.m_images[0];

	}

	@Override
	protected void loadSpecificAnimation () {
		// TODO Auto-generated method stub

	}

}
