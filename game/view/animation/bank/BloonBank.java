package view.animation.bank;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import view.animation.Sprite;


public class BloonBank extends EntityAnimationBank {

	public Sprite red;
	public Sprite blue;
	public Sprite green;
	public Sprite yellow;
	public Sprite pink;
	public Sprite purple;

	public Sprite Ired;
	public Sprite Iblue;
	public Sprite Igreen;
	public Sprite Iyellow;
	public Sprite Ipink;
	public Sprite Ipurple;


	private BloonBank () {
		loadSpecificAnimation(null);
		loadBasicAnimation(null);
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
		BufferedImage[] red = new BufferedImage[6];
		BufferedImage[] blue = new BufferedImage[6];
		BufferedImage[] green = new BufferedImage[6];
		BufferedImage[] yellow = new BufferedImage[6];
		BufferedImage[] pink = new BufferedImage[6];
		BufferedImage[] purple = new BufferedImage[6];

		for (int i = 0; i < 6; i++) {
			red[i] = loadImages("resources/red-balloon/" + ((int) (i + 1)) + ".png");
			blue[i] = loadImages("resources/blue-balloon/" + ((int) (i + 1)) + ".png");
			green[i] = loadImages("resources/green-balloon/" + ((int) (i + 1)) + ".png");
			yellow[i] = loadImages("resources/yellow-balloon/" + ((int) (i + 1)) + ".png");
			pink[i] = loadImages("resources/pink-balloon/" + ((int) (i + 1)) + ".png");
			purple[i] = loadImages("resources/purple-balloon/" + ((int) (i + 1)) + ".png");
		}

		this.red = new Sprite(red);
		this.blue = new Sprite(blue);
		this.green = new Sprite(green);
		this.yellow = new Sprite(yellow);
		this.pink = new Sprite(pink);
		this.purple = new Sprite(purple);

		BufferedImage[] redidle = new BufferedImage[1];
		redidle[0] = red[0];
		this.Ired = new Sprite(redidle);
		BufferedImage[] blueidle = new BufferedImage[1];
		blueidle[0] = blue[0];
		this.Iblue = new Sprite(blueidle);
		BufferedImage[] greenidle = new BufferedImage[1];
		greenidle[0] = green[0];
		this.Igreen = new Sprite(greenidle);
		BufferedImage[] yellowidle = new BufferedImage[1];
		yellowidle[0] = yellow[0];
		this.Iyellow = new Sprite(yellowidle);
		BufferedImage[] pinkidle = new BufferedImage[1];
		pinkidle[0] = pink[0];
		this.Ipink = new Sprite(pinkidle);
		BufferedImage[] purpleidle = new BufferedImage[1];
		purpleidle[0] = purple[0];
		this.Ipurple = new Sprite(purpleidle);

	}

	@Override
	protected void loadBasicAnimation (Sprite spriteFile) {
		//Dépend du sprite que l'on va trouver.
		BufferedImage[] idle_img = new BufferedImage[1];
		idle_img[0] = loadImages("resources/red-balloon/1.png");
		this.idle = new Sprite(idle_img);
		this.walk = this.idle;

	}

}
