package view.animation.bank;

import java.awt.image.BufferedImage;

import view.animation.Sprite;


public class DoorBank extends EntityAnimationBank {

	public Sprite opening;


	private DoorBank () {

		Sprite spriteFile = Sprite.loadSprite("resources/door.png", 2, 15);

		loadSpecificAnimation(spriteFile);
		loadBasicAnimation(spriteFile);
	}


	private static DoorBank INSTANCE = null;


	public static DoorBank getInstance () {

		if (INSTANCE == null) {
			INSTANCE = new DoorBank();
		}
		return INSTANCE;
	}

	@Override
	protected void loadBasicAnimation (Sprite spriteFile) {
		// TODO Auto-generated method stub
		BufferedImage[] idle_img = new BufferedImage[1];
		idle_img[0] = spriteFile.m_images[6];
		this.idle = new Sprite(idle_img);

		BufferedImage[] idle_walk = new BufferedImage[1];
		idle_walk[0] = spriteFile.m_images[14];
		this.idle = new Sprite(idle_img);

	}

	@Override
	protected void loadSpecificAnimation (Sprite spriteFile) {
		BufferedImage[] opening_img = new BufferedImage[7];

		for (int i = 0; i < 7; i++) {
			opening_img[i] = spriteFile.m_images[i + 6];
		}
		this.opening = new Sprite(opening_img);

	}

}
