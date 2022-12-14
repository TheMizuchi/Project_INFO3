package view.animation.bank;

import java.awt.image.BufferedImage;

import view.animation.Sprite;


public class J1Bank extends EntityAnimationBank {

	//Liste des aniations spécifique au J1;
	public Sprite attack;


	private J1Bank () {
		Sprite spriteFile = Sprite.loadSprite("resources/adventurer-v1.5-Sheet.png", 16, 7);

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
		BufferedImage[] idle_img = new BufferedImage[4];
		BufferedImage[] walk_img = new BufferedImage[5];

		for (int i = 0; i < 4; i++) {
			idle_img[i] = spriteFile.m_images[i];
		}
		this.idle = new Sprite(idle_img);

		for (int i = 0; i < 5; i++) {
			walk_img[i] = spriteFile.m_images[i + 9];
		}
		this.walk = new Sprite(walk_img);

	}

	@Override
	protected void loadSpecificAnimation (Sprite spriteFile) {
		BufferedImage[] attack_img = new BufferedImage[5];

		for (int i = 0; i < 5; i++) {
			attack_img[i] = spriteFile.m_images[i + 55];
		}
		this.attack = new Sprite(attack_img);

	}

}