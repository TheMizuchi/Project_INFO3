package view.animation.bank;

import java.awt.image.BufferedImage;

import view.AnimationBank;
import view.Sprite;


public class CowboyBank extends AnimationBank{

	// Liste des animations spécifique au cowboy
	public Sprite spin;
	
	public CowboyBank() {
		
		loadSpecificAnimation();
		loadBasicAnimation();
		
		
	}

	@Override
	protected void loadBasicAnimation () {
		BufferedImage[] turnLeftTab = new BufferedImage[13];
		for (int i = 12; i >= 0; i--) {
			turnLeftTab[i] = this.spin.m_images[(19 + i) % 24];
		}
		this.turnLeft = new Sprite(turnLeftTab, this.spin.m_width, this.spin.m_height);
		
		BufferedImage[] turnRightTab = new BufferedImage[13];
		for (int i = 0; i < 13; i++) {
			turnRightTab[i] = this.spin.m_images[(32 - i) % 24];
		}
		this.turnRight = new Sprite(turnRightTab, this.spin.m_width, this.spin.m_height);
		
		this.left = this.turnRight.m_images[0];
		this.right = this.turnLeft.m_images[0];
	}

	@Override
	protected void loadSpecificAnimation () {
		this.spin = Sprite.loadSprite("resources/winchester-4x6.png", 4, 6);
	}

}
