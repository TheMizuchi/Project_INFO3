package view.animation.bank;

import view.animation.Sprite;


public class DogeBank extends EntityAnimationBank {

	private DogeBank () {
		Sprite spriteFile = Sprite.loadSprite("resources/winchester-4x6.png", 4, 6);
		loadBasicAnimation(spriteFile);
		loadSpecificAnimation(spriteFile);
	}

	@Override
	protected void loadBasicAnimation (Sprite spriteFile) {
		// TODO Auto-generated method stub
		throw new RuntimeException("DogeBank NYI");
		
	}

	@Override
	protected void loadSpecificAnimation (Sprite spriteFile) {
		// TODO Auto-generated method stub
		throw new RuntimeException("DogeBank NYI");
	}

}
