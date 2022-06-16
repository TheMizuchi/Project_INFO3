package view.animation.bank;

import java.awt.image.BufferedImage;

import view.animation.Sprite;


public class BalloonBank extends EntityAnimationBank {

	public Sprite explode;


	private BalloonBank () {
		Sprite spriteFile = Sprite.loadSprite(null, 0, 0);
		loadSpecificAnimation(spriteFile);
		loadBasicAnimation(spriteFile);
	}


	private static BalloonBank INSTANCE = null;


	public static BalloonBank getInstance () {

		if (INSTANCE == null) {
			INSTANCE = new BalloonBank();
		}
		return INSTANCE;
	}

	@Override
	protected void loadSpecificAnimation (Sprite spriteFile) {
		//vérifier par rapport au sprite
		this.explode = spriteFile;

	}

	@Override
	protected void loadBasicAnimation (Sprite spriteFile) {
		//Dépend du sprite que l'on va trouver.

	}

}
