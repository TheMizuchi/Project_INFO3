package view.animation.bank;

import view.animation.Sprite;


public class TorchBank extends EntityAnimationBank {

	private TorchBank () {
		Sprite spriteFile = Sprite.loadSprite("resources/torch.png", 1, 6);
		loadBasicAnimation(spriteFile);
		loadSpecificAnimation(spriteFile);
	}


	private static TorchBank INSTANCE = null;


	public static TorchBank getInstance () {

		if (INSTANCE == null) {
			INSTANCE = new TorchBank();
		}
		return INSTANCE;
	}

	@Override
	protected void loadBasicAnimation (Sprite spriteFile) {
		this.idle = spriteFile;

	}

	@Override
	protected void loadSpecificAnimation (Sprite spriteFile) {}

}
