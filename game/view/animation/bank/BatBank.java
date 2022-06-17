package view.animation.bank;

import view.animation.Sprite;


public class BatBank extends EntityAnimationBank {

	private BatBank () {
		Sprite spriteFile = Sprite.loadSprite("Bat_Sprite_Sheet.png", 3, 5);
		loadBasicAnimation(spriteFile);
		loadSpecificAnimation(spriteFile);

	}


	private static BatBank INSTANCE = null;


	public static BatBank getInstance () {

		if (INSTANCE == null) {
			INSTANCE = new BatBank();
		}
		return INSTANCE;
	}

	@Override
	protected void loadBasicAnimation (Sprite spriteFile) {
		this.walk = spriteFile;
		this.idle = spriteFile;

	}

	@Override
	protected void loadSpecificAnimation (Sprite spriteFile) {
		// TODO Auto-generated method stub

	}

}
