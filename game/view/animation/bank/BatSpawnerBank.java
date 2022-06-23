package view.animation.bank;

import view.animation.Sprite;


public class BatSpawnerBank extends EntityAnimationBank {

	private BatSpawnerBank () {
		Sprite spriteFile = Sprite.loadSprite("resources/Spawner.png", 1, 1);
		loadBasicAnimation(spriteFile);
		loadSpecificAnimation(null);
	}


	private static BatSpawnerBank INSTANCE = null;


	public static BatSpawnerBank getInstance () {

		if (INSTANCE == null) {
			INSTANCE = new BatSpawnerBank();
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
