package view.animation.bank;

import view.animation.Sprite;


public class StairBank extends EntityAnimationBank {

	private StairBank () {
		Sprite spriteFile = Sprite.loadSprite("resources/stairs.png", 1, 6);
		loadBasicAnimation(spriteFile);
		loadSpecificAnimation(spriteFile);
	}


	private static StairBank INSTANCE = null;


	public static StairBank getInstance () {

		if (INSTANCE == null) {
			INSTANCE = new StairBank();
		}
		return INSTANCE;
	}

	@Override
	protected void loadBasicAnimation (Sprite spriteFile) {
		this.idle = spriteFile;

	}

	@Override
	protected void loadSpecificAnimation (Sprite spriteFile) {}

}
