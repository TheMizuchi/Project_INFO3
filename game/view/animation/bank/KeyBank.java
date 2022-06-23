package view.animation.bank;

import view.animation.Sprite;


public class KeyBank extends EntityAnimationBank {

	private KeyBank () {
		Sprite spriteFile = Sprite.loadSprite("resources/key.png", 1, 1);
		loadBasicAnimation(spriteFile);
		loadSpecificAnimation(spriteFile);
	}


	private static KeyBank INSTANCE = null;


	public static KeyBank getInstance () {

		if (INSTANCE == null) {
			INSTANCE = new KeyBank();
		}
		return INSTANCE;
	}

	@Override
	protected void loadBasicAnimation (Sprite spriteFile) {
		// TODO Auto-generated method stub
		this.idle = spriteFile;

	}

	@Override
	protected void loadSpecificAnimation (Sprite spriteFile) {
		// TODO Auto-generated method stub

	}

}
