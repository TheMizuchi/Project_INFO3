package view.animation.bank;

import java.awt.image.BufferedImage;

import view.animation.Sprite;


public class ArrowBank extends EntityAnimationBank {

	public Sprite hautgauche;
	public Sprite haut;
	public Sprite hautdroite;
	public Sprite basgauche;
	public Sprite bas;
	public Sprite basdroite;


	private ArrowBank () {
		Sprite spriteFile = Sprite.loadSprite("resources/arrow.png", 1, 8);

		loadBasicAnimation(spriteFile);
		loadSpecificAnimation(spriteFile);
	}


	private static ArrowBank INSTANCE = null;


	public static ArrowBank getInstance () {

		if (INSTANCE == null) {
			INSTANCE = new ArrowBank();
		}
		return INSTANCE;
	}

	@Override
	protected void loadBasicAnimation (Sprite spriteFile) {
		BufferedImage[] idle_img = new BufferedImage[1];
		BufferedImage[] walk_img = new BufferedImage[1];

		idle_img[0] = spriteFile.m_images[0];
		walk_img[0] = spriteFile.m_images[2];

		this.idle = new Sprite(idle_img);
		this.walk = new Sprite(walk_img);

	}

	@Override
	protected void loadSpecificAnimation (Sprite spriteFile) {
		BufferedImage[] hautgauche_img = new BufferedImage[1];
		BufferedImage[] haut_img = new BufferedImage[1];
		BufferedImage[] hautdroite_img = new BufferedImage[1];
		BufferedImage[] basgauche_img = new BufferedImage[1];
		BufferedImage[] bas_img = new BufferedImage[1];
		BufferedImage[] basdroite_img = new BufferedImage[1];

		hautgauche_img[0] = spriteFile.m_images[5];
		haut_img[0] = spriteFile.m_images[2];
		hautdroite_img[0] = spriteFile.m_images[7];
		basgauche_img[0] = spriteFile.m_images[6];
		bas_img[0] = spriteFile.m_images[3];
		basdroite_img[0] = spriteFile.m_images[4];

		this.hautgauche = new Sprite(hautgauche_img);
		this.haut = new Sprite(haut_img);
		this.hautdroite = new Sprite(hautdroite_img);
		this.basgauche = new Sprite(basgauche_img);
		this.bas = new Sprite(bas_img);
		this.basdroite = new Sprite(basdroite_img);

	}
}
