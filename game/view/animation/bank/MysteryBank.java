package view.animation.bank;

import view.animation.Sprite;


public class MysteryBank extends EntityAnimationBank {

	public Sprite angry;


	private MysteryBank () {
		Sprite spriteFile = Sprite.loadSprite("resources/Mystery.png", 1, 1);
		loadBasicAnimation(spriteFile);
		loadSpecificAnimation(null);
	}


	private static MysteryBank INSTANCE = null;


	public static MysteryBank getInstance () {

		if (INSTANCE == null) {
			INSTANCE = new MysteryBank();
		}
		return INSTANCE;
	}

	@Override
	protected void loadBasicAnimation (Sprite spriteFile) {
		this.idle = spriteFile;
		this.walk = spriteFile;
	}

	@Override
	protected void loadSpecificAnimation (Sprite spriteFile) {}
}
