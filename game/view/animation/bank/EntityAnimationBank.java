package view.animation.bank;

import java.awt.image.BufferedImage;

import view.animation.Sprite;


public abstract class EntityAnimationBank {

	// Liste des états 
	public Sprite turnLeft;
	public Sprite turnRight;

	// Liste des états persistants fixes
	public BufferedImage left;
	public BufferedImage right;


	public static void loadAnimations () {

	}

	protected abstract void loadBasicAnimation (Sprite spriteFile); // load sprite to fill field in this class
	protected abstract void loadSpecificAnimation (Sprite spriteFile); // load sprite to fill field in the concrete class that extends this one
}
