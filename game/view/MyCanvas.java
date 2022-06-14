package view;

import java.awt.Component;
import java.awt.Graphics;

import edu.polytech.oop.collections.LinkedList;
import view.animation.bank.AnimationBank;
import view.animation.bank.CowboyBank;
import view.graphicEntity.CowboyView;


public class MyCanvas extends Component {

	private static final long serialVersionUID = 1L;

	int win_h;
	int win_w;
	int x, y;
	LinkedList m_entityViews;
	TorcheView m_torche;
	MapView m_map;
	Viewport vp;
	public FakeModel fm;
	public static AnimationBank[] animationBank;


	public MyCanvas (int win_w, int win_h) {
		this.win_h = win_h;
		this.win_w = win_w;
		m_entityViews = new LinkedList();
		AnimationBank.getInstance();
		this.vp = new Viewport(win_w, win_h);

		this.fm = new FakeModel(this);
		run();
	}

	/*
	 * This method is temporary. It is only here too test View
	 * 
	 * 
	 */

	void run () {

		m_map = new MapView(this.fm.map.getMap());
		int res = m_map.hauteur();
		System.out.println("hauteur" + res);
		res = m_map.largeur();
		System.out.println("largeur" + res);

		m_torche = new TorcheView(960, 540, 200, this.win_w, this.win_h, 1, this.fm.torch);

		vp.setPosition(0, 0, 1);
	}

	/*
	 * 
	 * 
	 * 
	 * This method should be called in every EntityConstructor. It create an
	 * EntityView to associate to the Entity. It takes an EntityID to identify which
	 * EntityView should be created here and the Entity.
	 */

	//	EntityView createEntityView (EntityInterface entity) {
	//TODO Demande la création d'une EntityView pour une entité ( à définir quand les entités du Model seront prêtes )
	//		CowboyView c = new CowboyView(0, 0, 1, entity, (CowboyBank) this.animationBank[FakeModel.CowboyID]);
	//		m_entityViews.insertAt(0, c);
	//		c.spin();
	//		return c;

	//	}
	void createEntityView (EntityView entity) {
		m_entityViews.insertAt(0, entity);
	}

	void updateView () {
		/*
		 * TODO Demander à tous les éléments contenus dans le Viewport uniquement
		 * d'actualiser leurs informations et de les convertir en information locale au
		 * Viewport
		 */

		LinkedList.Iterator it = m_entityViews.iterator();

		while (it.hasNext()) {
			EntityView e = ((EntityView) it.next());
			//Code à décommenter quand la communication entre entityModel et EntityView sera mise en place
			e.setPosition(vp.toLocalX(e.entity.getPosX()), vp.toLocalY(e.entity.getPosY()), vp.scale);

		}
		m_torche.setPosition(vp.toLocalX(m_torche.torchModel.getPosX()), vp.toLocalY(m_torche.torchModel.getPosY()), vp.scale);
		m_map.setPosition(vp.toLocalX(0), vp.toLocalY(0), vp.scale);
	}

	@Override
	public void paint (Graphics g) {
		this.updateView();
		m_map.paint(g);
		LinkedList.Iterator it = m_entityViews.iterator();
		int i = 0;
		while (it.hasNext()) {
			EntityView e = (EntityView) it.next();

			if (vp.isInside(e.x, e.y)) {
				i++;
				e.paint(g);
			}
		}
		System.out.println("Nb d'entité dessiné : " + i);

		//m_torche.paint(g);

	}

}
