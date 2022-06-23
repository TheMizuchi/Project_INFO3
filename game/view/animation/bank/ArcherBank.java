package view.animation.bank;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import view.animation.Sprite;


public class ArcherBank extends EntityAnimationBank {

	// Liste des animations spécifique au Archer
	public Sprite attack;


	private ArcherBank () {
		;
		loadBasicAnimation(null);
		loadSpecificAnimation(null);

	}


	private static ArcherBank INSTANCE = null;


	public static ArcherBank getInstance () {

		if (INSTANCE == null) {
			INSTANCE = new ArcherBank();
		}
		return INSTANCE;
	}

	private BufferedImage loadImages (String filename) {
		File imageFile = new File(filename);

		if (imageFile.exists()) {
			BufferedImage image = null;

			try {
				image = ImageIO.read(imageFile);
			}
			catch (IOException e) {
				System.out.println("Impossible d'ouvrir le fichier " + filename);
				e.printStackTrace();
			}
			return image;
		}
		return null;

	}

	@Override
	protected void loadBasicAnimation (Sprite spriteFile) {
		BufferedImage[] idle_img = new BufferedImage[10];
		BufferedImage[] walk_img = new BufferedImage[22];

		for (int i = 0; i < 10; i++) {
			idle_img[i] = loadImages("resources/archer/Idle/idle_test_00" + i + ".png");
		}
		this.idle = new Sprite(idle_img);

		for (int i = 0; i < 10; i++) {
			walk_img[i] = loadImages("resources/archer/Run/run_idle_00" + i + ".png");
		}

		for (int i = 10; i < 22; i++) {
			walk_img[i] = loadImages("resources/archer/Run/run_idle_0" + i + ".png");
		}
		this.walk = new Sprite(walk_img);
	}

	@Override
	protected void loadSpecificAnimation (Sprite spriteFile) {
		BufferedImage[] atk_img = new BufferedImage[22];

		for (int i = 0; i < 10; i++) {
			atk_img[i] = loadImages("resources/archer/Attack/shoot_stand_00" + i + ".png");
		}

		for (int i = 10; i < 22; i++) {
			atk_img[i] = loadImages("resources/archer/Attack/shoot_stand_0" + i + ".png");
		}

		this.attack = new Sprite(atk_img);
	}
}
