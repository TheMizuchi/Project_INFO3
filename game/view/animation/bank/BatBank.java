package view.animation.bank;

import java.awt.image.BufferedImage;

import view.animation.Sprite;


public class BatBank extends EntityAnimationBank {

	private BatBank () {
		Sprite spriteFile = Sprite.loadSprite("resources/Bat_Sprite_Sheet.png", 3, 5);
		
		BufferedImage[] idle_img = new BufferedImage[4];
		for (int i = 0; i < 4; i++) {
			idle_img[i] = spriteFile.m_images[i];
		}
		Sprite spritevol = new Sprite(idle_img);
		
		loadBasicAnimation(spritevol);
		loadSpecificAnimation(spritevol);

	}


	private static BatBank INSTANCE = null;


	public static BatBank getInstance () {

		if (INSTANCE == null) {
			INSTANCE = new BatBank();
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
		// TODO Auto-generated method stub

	}

}
