package view.animation.bank;

import java.awt.image.BufferedImage;

import view.animation.Sprite;


public class CowboyBank extends EntityAnimationBank {

	// Liste des animations spécifique au cowboy
	public Sprite spin;
	public Sprite turn;

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
		BufferedImage[] turnTab = new BufferedImage[13];

		for (int i = 12; i >= 0; i--) {
			turnTab[i] = spriteFile.m_images[(19 + i) % 24];
		}
		this.turn = new Sprite(turnTab);
		
		BufferedImage[] idleTab = new BufferedImage[1];
		idleTab[0] = spriteFile.m_images[20];
		this.idle = new Sprite(idleTab);
	}

	@Override
	protected void loadSpecificAnimation (Sprite spriteFile) {
		this.spin = spriteFile;
	}

}
