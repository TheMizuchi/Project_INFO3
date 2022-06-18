package view.animation.bank;

import view.animation.Sprite;

public class SkeletonBank extends EntityAnimationBank {
	
	public Sprite attack;
	
	private SkeletonBank () {
		loadBasicAnimation(null);
		loadSpecificAnimation(null);
	}


	private static SkeletonBank INSTANCE = null;


	public static SkeletonBank getInstance () {

		if (INSTANCE == null) {
			INSTANCE = new SkeletonBank();
		}
		return INSTANCE;
	}

	@Override
	protected void loadBasicAnimation (Sprite spriteFile) {
		Sprite spriteFileIdle = Sprite.loadSprite("resources/Skeleton Idle.png", 1, 11);
		Sprite spriteFileWalk = Sprite.loadSprite("resources/Skeleton Walk.png", 1, 13);
		this.idle = spriteFileIdle;
		this.walk = spriteFileWalk;
		
	}

	@Override
	protected void loadSpecificAnimation (Sprite spriteFile) {
		Sprite spriteFileAttack = Sprite.loadSprite("resources/Skeleton Attack.png", 1, 18);
		this.attack = spriteFileAttack;
		
	}

}
