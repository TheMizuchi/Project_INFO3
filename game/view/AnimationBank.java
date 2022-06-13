package view;

import java.awt.image.BufferedImage;


public abstract class AnimationBank {

	// Liste des états 
	public Sprite turnLeft;
	public Sprite turnRight;

	// Liste des états persistants fixes
	public BufferedImage left;
	public BufferedImage right;
	
	public static void loadAnimations() {
		
	}
	
	protected abstract void loadBasicAnimation(); // load sprite to fill field in this class
	protected abstract void loadSpecificAnimation(); // load sprite to fill field in the concrete class that extends this one
}
