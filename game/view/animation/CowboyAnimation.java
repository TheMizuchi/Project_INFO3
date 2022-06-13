package view.animation;

import java.awt.image.BufferedImage;

import view.Animation;
import view.Sprite;


public class CowboyAnimation extends Animation {
	
	// Liste des animations
	private Sprite spin;
	private Sprite turnLeft;
	private Sprite turnRight;
	private BufferedImage left;
	private BufferedImage right;


	public CowboyAnimation () {
		super();
		this.spin = Sprite.loadSprite("resources/winchester-4x6.png", 4, 6);
		BufferedImage[] turnLeftTab = new BufferedImage[13];
		 for(int i = 12; i>=0; i--){
			turnLeftTab[i] = this.spin.m_images[(19+i)%24];
		}
		this.turnLeft = new Sprite(turnLeftTab, this.spin.m_width, this.spin.m_height);
		BufferedImage[] turnRightTab = new BufferedImage[13];

		for (int i = 0; i < 13; i++) {
			turnRightTab[i] = this.spin.m_images[(32-i)%24];
		}
		this.turnRight = new Sprite(turnRightTab, this.spin.m_width, this.spin.m_height);
		this.left = this.turnRight.m_images[0];
		this.right = this.turnLeft.m_images[0];
	}
	
	public void spin () {
		m_sprite = this.spin;
		this.start();
	}
	
	public void turnLeft() {
		m_sprite = this.turnLeft;
		this.left();
		this.start();
	}
	
	public void turnRight() {
		m_sprite = this.turnRight;
		this.right();
		this.start();
	}
	
	public void left() {
		m_fixImage = this.left;
	}

	public void right() {
		m_fixImage = this.right;
	}

}
