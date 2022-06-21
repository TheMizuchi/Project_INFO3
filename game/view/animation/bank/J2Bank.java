package view.animation.bank;

import view.animation.Sprite;


public class J2Bank extends EntityAnimationBank {

	//Liste des aniations spécifique au J2;
	public Sprite attack;


	private J2Bank () {
		loadBasicAnimation(null);
		loadSpecificAnimation(null);
	}


	private static J2Bank INSTANCE = null;


	public static J2Bank getInstance () {

		if (INSTANCE == null) {
			INSTANCE = new J2Bank();
		}
		return INSTANCE;
	}

	@Override
	protected void loadBasicAnimation (Sprite spriteFile) {
		this.idle = Sprite.loadSprite("resources/j2Idle.png", 1, 10);
		this.walk = Sprite.loadSprite("resources/j2Run.png", 1, 8);

	}

	@Override
	protected void loadSpecificAnimation (Sprite spriteFile) {
		this.attack = Sprite.loadSprite("resources/j2Attack.png", 1, 6);

	}

}