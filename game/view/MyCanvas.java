package view;

import java.awt.Component;
import java.awt.Graphics;

import edu.polytech.oop.collections.LinkedList;
import view.animation.bank.AnimationBank;
import view.animation.bank.CowboyBank;
import view.graphicEntity.CowboyView;
import view.graphicEntity.LightSourceView;


public class MyCanvas extends Component {

	public static final int METRIC_BASE = 25;
	private static final long serialVersionUID = 1L;

	int win_h;
	int win_w;
	int x, y;
	LinkedList m_entityViews;
	LightView m_light;
	MapView m_map;
	Viewport vp;


	private MyCanvas () {
		m_entityViews = new LinkedList();
		AnimationBank.getInstance();
	}


	private static MyCanvas INSTANCE = null;


	public static MyCanvas getInstance () {

		if (INSTANCE == null) {
			INSTANCE = new MyCanvas();
		}
		return INSTANCE;
	}

	public void setDim (int w, int h) {
		this.win_h = h;
		this.win_w = w;
		this.vp = new Viewport(win_w, win_h);
		m_light = new LightView(this.win_w, this.win_h, 1);
	}

	public void createEntityView (EntityView entity) {
		m_entityViews.insertAt(0, entity);
	}

	public void createLightSourceView (ILightSource s) {
		m_light.addLightSource(s);
	}

	void updateView () {

		LinkedList.Iterator it = m_entityViews.iterator();

		while (it.hasNext()) {
			EntityView e = ((EntityView) it.next());
			e.setPosition(vp.toLocalX(e.entity.getPosX()), vp.toLocalY(e.entity.getPosY()), vp.scale);

		}

		it = m_light.lightSource.iterator();

		while (it.hasNext()) {
			LightSourceView s = (LightSourceView) it.next();
			// Ici, les méthodes get*****(); servent à récupérer les informations de la source de lumière du model et non pas de la view 
			s.setPosition(vp.toLocalX(s.getPosX()), vp.toLocalY(s.getPosY()), vp.scale, (int) (vp.scale * s.getRadius()));
		}
		//décommenter quand la map sera dispo 
		//m_map.setPosition(vp.toLocalX(0), vp.toLocalY(0), vp.scale);
	}

	@Override
	public void paint (Graphics g) {
		this.updateView();
		//décommenter quand la map sera dispo 
		//m_map.paint(g);
		LinkedList.Iterator it = m_entityViews.iterator();
		int i = 0;

		while (it.hasNext()) {
			EntityView e = (EntityView) it.next();

			if (vp.isInside(e.x, e.y, e.getW(), e.getH())) {//&& m_light.isInside(e.x, e.y)) {
				i++;
				e.paint(g);
			}
		}
		//System.out.println("Nb d'entité dessiné : " + i);

		//m_light.paint(g);

	}

}
