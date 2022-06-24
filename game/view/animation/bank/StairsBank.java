package view.animation.bank;

import view.animation.Sprite;


public class StairsBank extends EntityAnimationBank {

	private StairsBank () {
		Sprite spriteFile = Sprite.loadSprite("resources/stairs.png", 1, 1);
		loadBasicAnimation(spriteFile);
		loadSpecificAnimation(spriteFile);
	}


	private static StairsBank INSTANCE = null;


	public static StairsBank getInstance () {

		if (INSTANCE == null) {
			INSTANCE = new StairsBank();
		}
		return INSTANCE;
		
	}
	@Override
	protected void loadBasicAnimation (Sprite spriteFile) {
		this.idle = spriteFile;

	}

	@Override
	protected void loadSpecificAnimation (Sprite spriteFile) {
		// TODO Auto-generated method stub

	}

}
