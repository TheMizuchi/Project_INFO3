package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import edu.polytech.oop.collections.LinkedList;
import model.ILightSource;
import view.graphicEntity.LightSourceView;


public class LightView {

	private LinkedList lightSource; // Liste chainée de ILightSource
	private double scale;
	private int win_h, win_w;


	public LightView (int win_w, int win_h, double scale) {
		this.win_h = win_h;
		this.win_w = win_w;
		this.scale = scale;
		this.lightSource = new LinkedList();

	}

	public LinkedList getLightSources () {
		return this.lightSource;
	}

	public void setPosition (double scale) {
		this.scale = scale;

	}

	void addLightSource (ILightSource s) {
		this.lightSource.insertAt(0, s);
	}

	boolean isInside (int x, int y) {
		LinkedList.Iterator it = this.lightSource.iterator();

		while (it.hasNext()) {
			LightSourceView l = (LightSourceView) it.next();
			int rad = (int) (l.radius * this.scale);
			if ((x - l.x) * (x - l.x) + (y - l.y) * (y - l.y) < rad * rad)
				return true;
		}
		return false;
	}

	void paint (Graphics g) { // Ce paint doit être appelé en dernier, c'est le masque qui recouvre tous les éléments qui ne sont pas sensé être visible
		LinkedList.Iterator it = this.lightSource.iterator();
		Graphics2D g2d = (Graphics2D) g;
		Area mask = new Area(new Rectangle2D.Double(0, 0, this.win_w, this.win_h));
		Area crop = new Area();

		while (it.hasNext()) {
			LightSourceView l = (LightSourceView) it.next();
			int rad = (int) (l.radius * this.scale);
			crop.add(new Area(new Ellipse2D.Double(l.x - rad / 2, l.y - rad / 2, rad, rad)));

			//			for (int i = 0; i < 41; i++) { // Dessine le fondu vers le noir au bord du champ de vision pour que la transtion soit plus progressive
			//				g2d.setColor(new Color(0, 0, 0, 6 * i));
			//				g2d.drawOval(l.x + (40 - i - rad) / 2, l.y + (40 - i-rad) / 2, rad - 38 + i, rad - 38 + i);
			//			}
		}
		mask.subtract(crop);

		g2d.setColor(Color.black);
		g2d.fill(mask);

	}
}
