package view.animation.bank;

import view.animation.Sprite;


public class DogeBank extends EntityAnimationBank {

	public Sprite angry;


	private DogeBank () {
		Sprite spriteFile = Sprite.loadSprite("resources/BaseDoge.png", 1, 1);
		loadBasicAnimation(spriteFile);
		loadSpecificAnimation(null);
	}


	private static DogeBank INSTANCE = null;


	public static DogeBank getInstance () {

		if (INSTANCE == null) {
			INSTANCE = new DogeBank();
		}
		return INSTANCE;
	}

	@Override
	protected void loadBasicAnimation (Sprite spriteFile) {
		this.idle = spriteFile;
		this.walk = spriteFile;
	}

	@Override
	protected void loadSpecificAnimation (Sprite spriteFile) {
		this.angry = Sprite.loadSprite("resources/AngryDoge.png", 1, 1);
	}

}
