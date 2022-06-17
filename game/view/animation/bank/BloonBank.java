package view.animation.bank;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import view.animation.Sprite;


public class BloonBank extends EntityAnimationBank {

	public Sprite explode;


	private BloonBank () {
		BufferedImage[] images = new BufferedImage[6];
		for(int i = 0; i<6; i++) {
			images[i] = loadImages("resources/green-balloon/"+((int)(i+1))+".png");
		}

		Sprite spriteFile = new Sprite(images);
		loadSpecificAnimation(spriteFile);
		loadBasicAnimation(spriteFile);
	}


	private static BloonBank INSTANCE = null;


	public static BloonBank getInstance () {

		if (INSTANCE == null) {
			INSTANCE = new BloonBank();
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
	protected void loadSpecificAnimation (Sprite spriteFile) {
		//vérifier par rapport au sprite
		this.explode = spriteFile;

	}

	@Override
	protected void loadBasicAnimation (Sprite spriteFile) {
		//Dépend du sprite que l'on va trouver.
		BufferedImage[] idle_img = new BufferedImage[1];
		idle_img[0] = spriteFile.m_images[0];
		this.idle = new Sprite(idle_img);

	}

}
