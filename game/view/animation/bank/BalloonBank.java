package view.animation.bank;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import view.animation.Sprite;


public class BalloonBank extends EntityAnimationBank {

	public Sprite explode;


	private BalloonBank () {
		BufferedImage[] images = new BufferedImage[6];
		for(int i = 0; i<6; i++) {
			images[i] = loadImages("ressources/green-ballon/"+i+1+".png");
		}
		
		Sprite spriteFile = new Sprite(images, 0, 0);
		loadSpecificAnimation(spriteFile);
		loadBasicAnimation(spriteFile);
	}


	private static BalloonBank INSTANCE = null;


	public static BalloonBank getInstance () {

		if (INSTANCE == null) {
			INSTANCE = new BalloonBank();
		}
		return INSTANCE;
	}
	
	private BufferedImage loadImages(String filename) {
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

	}

}
