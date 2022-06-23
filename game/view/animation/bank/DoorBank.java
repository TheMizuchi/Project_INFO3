package view.animation.bank;

import java.awt.image.BufferedImage;

import view.animation.Sprite;


public class DoorBank extends EntityAnimationBank {

	public Sprite opening;
	public Sprite closing;
	public Sprite lat_idle;
	public Sprite lat_opening;
	public Sprite lat_closing;
	public Sprite lat_closed;


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

		BufferedImage[] walk_img = new BufferedImage[1];
		walk_img[0] = spriteFile.m_images[14];
		this.walk = new Sprite(walk_img);

	}

	@Override
	protected void loadSpecificAnimation (Sprite spriteFile) {
		BufferedImage[] opening_img = new BufferedImage[7];

		for (int i = 0; i < 7; i++) {
			opening_img[i] = spriteFile.m_images[i + 6];
		}
		this.opening = new Sprite(opening_img);

		BufferedImage[] closing_img = new BufferedImage[7];
		int x = 0;

		for (int j = 14; j > 7; j--) {
			closing_img[x] = spriteFile.m_images[j];
			x++;
		}
		this.closing = new Sprite(closing_img);
		
		BufferedImage[] lat_idle_img = new BufferedImage[1];

		lat_idle_img[0] = spriteFile.m_images[21];
		this.lat_opening = new Sprite(lat_idle_img);
		
		BufferedImage[] lat_opening_img = new BufferedImage[7];

		for (int i = 0; i < 7; i++) {
			lat_opening_img[i] = spriteFile.m_images[i + 21];
		}
		this.lat_opening = new Sprite(lat_opening_img);
		
		BufferedImage[] lat_closing_img = new BufferedImage[7];
		int y = 0;

		for (int j = 29; j > 7; j--) {
			lat_closing_img[y] = spriteFile.m_images[j];
			y++;
		}
		this.lat_closing = new Sprite(lat_closing_img);
		
		BufferedImage[] lat_closed_img = new BufferedImage[7];
		lat_closed_img[0] = spriteFile.m_images[29];
		this.lat_closed= new Sprite(lat_closed_img);

	}

}
