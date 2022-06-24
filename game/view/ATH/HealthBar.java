package view.ATH;

import java.awt.Color;
import java.awt.Graphics;

import model.entity.Player;
import view.ViewElement;


public class HealthBar extends ViewElement {

	int h, w;
	Color c;
	int pv;


	public HealthBar (int x, int y, Color c, int pv) {
		super(x, y, 1);
		this.h = 20;
		this.w = 500;
		this.c = c;
		this.pv = pv;
	}

	@Override
	protected void paint (Graphics g) {
		g.setColor(Color.black);
		g.drawRect(this.x, this.y, this.w, this.h);
		g.setColor(this.c);
		g.fillRect(this.x + 1, this.y + 1, ((this.w)/20)*this.pv - 1, this.h - 1);
		//quand les joueurs auront de la vie, il faudra faire afficher le barre en fonction de ratio vie/vieMax
	}

}
