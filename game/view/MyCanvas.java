package view;

import java.awt.Component;
import java.awt.Graphics;

import edu.polytech.oop.collections.LinkedList;
import view.graphicEntity.CowboyView;


public class MyCanvas extends Component {

	public static double c_scale = 2; // Constant that define the scale base

	private static final long serialVersionUID = 1L;

	int win_h;
	int win_w;
	double scale;
	int x, y;
	LinkedList m_entityViews;
	TorcheView m_torche;
	Viewport vp;


	public MyCanvas (int win_w, int win_h) {
		this.scale = 2;
		this.win_h = win_h;
		this.win_w = win_h;
		m_entityViews = new LinkedList();
		this.vp = new Viewport();

		run();

	}

	/* This method is temporary. It is only here too test View */

	void run () {

		for (int i = 0; i < 144; i++) {
			CowboyView c = new CowboyView(100 + 100 * (i % 18), 100 + 100 * ((int) (i / 18)), this.scale);
			m_entityViews.insertAt(0, c);
			c.spin();
		}
		m_torche = new TorcheView(960, 540, 200, this.win_w, this.win_h, this.scale);
	}

	/*
	 * This method should be called in every EntityConstructor. It create an
	 * EntityView to associate to the Entity. It takes an EntityID to identify which
	 * EntityView should be created here and the Entity.
	 */

	EntityView createEntityView (int EntityID, EntityInterface Entity) {
		//TODO Demande la création d'une EntityView pour une entité
		return null;
	}

	void updateView () {
		/*
		 * TODO Demander à tous les éléments contenus dans le Viewport uniquement
		 * d'actualiser leurs informations et de les convertir en information locale au
		 * Viewport
		 */
	}

	@Override
	public void paint (Graphics g) {
		this.updateView();
		LinkedList.Iterator it = m_entityViews.iterator();

		while (it.hasNext()) {
			((EntityView) it.next()).paint(g);
		}
		m_torche.paint(g);
	}

}
