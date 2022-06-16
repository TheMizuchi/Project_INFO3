package view.animation.bank;

import java.awt.image.BufferedImage;

import view.animation.Sprite;


public class CowboyBank extends EntityAnimationBank {

	// Liste des animations spécifique au cowboy
	public Sprite spin;


	private CowboyBank () {
		Sprite spriteFile = Sprite.loadSprite("resources/winchester-4x6.png", 4, 6);
		loadBasicAnimation(spriteFile);
		loadSpecificAnimation(spriteFile);

	}


	private static CowboyBank INSTANCE = null;


	public static CowboyBank getInstance () {

		if (INSTANCE == null) {
			INSTANCE = new CowboyBank();
		}
		return INSTANCE;
	}

	@Override
	protected void loadBasicAnimation (Sprite spriteFile) {
		BufferedImage[] turnLeftTab = new BufferedImage[13];

		for (int i = 12; i >= 0; i--) {
			turnLeftTab[i] = spriteFile.m_images[(19 + i) % 24];
		}
		this.turnLeft = new Sprite(turnLeftTab);

		BufferedImage[] turnRightTab = new BufferedImage[13];

		for (int i = 0; i < 13; i++) {
			turnRightTab[i] = spriteFile.m_images[(32 - i) % 24];
		}
		this.turnRight = new Sprite(turnRightTab);

		this.left = this.turnRight.m_images[0];
		this.right = this.turnLeft.m_images[0];
	}

	@Override
	protected void loadSpecificAnimation (Sprite spriteFile) {
		this.spin = spriteFile;
	}

}
