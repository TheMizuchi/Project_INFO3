package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;


public class TorcheView extends ViewElement {

	int x, y;
	double radius;
	double scale;
	int win_h, win_w;


	public TorcheView (int x, int y, double radius, int win_h, int win_w, double scale) {
		this.radius = radius * scale;
		this.win_h = win_h;
		this.win_w = win_w;
		this.scale = scale;
		this.x = (int) (x * this.scale - this.radius / 2);
		this.y = (int) (y * this.scale - this.radius / 2);
	}

	public void setPos (int x, int y, double radius, double scale) {
		this.scale = scale;
		this.radius = radius * scale;
		this.x = (int) (this.x * this.scale - this.radius);
		this.y = (int) (this.y * this.scale - this.radius);
	}

	@Override
	void paint (Graphics g) { // Ce paint doit être appelé en dernier, c'est la masque qui recouvre tous les éléments qui ne sont pas sensé être visible
		Graphics2D g2d = (Graphics2D) g;
		Area mask = new Area(new Rectangle2D.Double(0, 0, this.win_w, this.win_h));
		Area mask_circle = new Area(new Ellipse2D.Double(this.x, this.y, this.radius, this.radius));
		mask.subtract(mask_circle);
		g2d.setColor(Color.black);
		g2d.fill(mask);
		
		for (int i = 0; i < 40; i++) { // Dessine le fondu vers le noir au bord du champ de vision pour que la transtion soit plus progressive
			g2d.setColor(new Color(0, 0, 0, 6 * i));
			g2d.drawOval(this.x+(40-i)/2, this.y+(40-i)/2, (int) this.radius - 38 + i, (int) this.radius - 38 + i);
		}
	}

}
