package view.animation.bank;

import view.animation.Sprite;


public class J1Bank extends EntityAnimationBank {

	//Liste des aniations spécifique au J1;
	public Sprite attack;


	private J1Bank () {
		Sprite spriteFile = Sprite.loadSprite("resources/winchester-4x6.png", 4, 6);
		loadBasicAnimation(spriteFile);
		loadSpecificAnimation(spriteFile);
	}


	private static J1Bank INSTANCE = null;


	public static J1Bank getInstance () {

		if (INSTANCE == null) {
			INSTANCE = new J1Bank();
		}
		return INSTANCE;
	}

	@Override
	protected void loadBasicAnimation (Sprite spriteFile) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void loadSpecificAnimation (Sprite spriteFile) {
		// TODO Auto-generated method stub

	}

}
