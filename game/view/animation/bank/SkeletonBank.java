package view.animation.bank;

import view.animation.Sprite;

public class SkeletonBank extends EntityAnimationBank {
	
	public Sprite attack;
	
	private SkeletonBank () {
		Sprite spriteFile = Sprite.loadSprite("Skeleton Idle.png", 1, 11);

		loadBasicAnimation(spriteFile);
		loadSpecificAnimation(spriteFile);
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
		Sprite spriteFileIdle = Sprite.loadSprite("Skeleton Idle.png", 1, 11);
		Sprite spriteFileWalk = Sprite.loadSprite("Skeleton Walk.png", 1, 13);
		this.idle = spriteFileIdle;
		this.walk = spriteFileWalk;
		
	}

	@Override
	protected void loadSpecificAnimation (Sprite spriteFile) {
		Sprite spriteFileAttack = Sprite.loadSprite("Skeleton Attack.png", 1, 18);
		this.attack = spriteFileAttack;
		
	}

}
