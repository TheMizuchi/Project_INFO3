package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import edu.polytech.oop.collections.IList;
import edu.polytech.oop.collections.LinkedList;
import model.ILightSource;
import model.entity.Entity;
import model.entity.Player;
import model.map.Case;
import view.ATH.ATH;
import view.animation.bank.AnimationBank;
import view.graphicEntity.EntityView;
import view.graphicEntity.LightSourceView;


public class MyCanvas extends Component {

	// Pour le zoom de base, 1m = 25 pixels
	public static final int METRIC_BASE = 30;
	private static final long serialVersionUID = 1L;

	// Dimension de la fenêtre
	int win_h;
	int win_w;

	// Unique instance du canvas
	private static MyCanvas INSTANCE = null;

	// Objets graphiques liées aux objets du Model
	LinkedList m_entityViews;
	LinkedList m_toClearEntity;
	LightView m_light;
	MapView m_map;
	ATH m_ath;

	// Objets graphiques locaux
	Viewport vp;


	private MyCanvas () {
		m_entityViews = new LinkedList();
		m_toClearEntity = new LinkedList();
		AnimationBank.getInstance();
	}

	public static MyCanvas getInstance () {

		if (INSTANCE == null) {
			INSTANCE = new MyCanvas();
		}
		return INSTANCE;
	}

	/*
	 * Quand le canvas est créé, il faut appeler cette méthode pour initier le
	 * canvas. Si ce n'est pas fait, les éléments graphiques ne peuvent pas être
	 * instanciés.
	 */

	public void initCanvas (int w, int h) {
		this.win_h = h;
		this.win_w = w;
		this.vp = new Viewport(win_w, win_h);
		m_light = new LightView(this.win_w, this.win_h, 1);
	}

	public void initATH (Player j1, Player j2) {
		m_ath = ATH.getInstance(j1, j2);
	}

	/*
	 * Notifications pour associer des Objets graphiques aux objets du model
	 */

	public void createEntityView (EntityView entity) {
		m_entityViews.insertAt(0, entity);
	}

	// Je m'en fou si t'es pas content Killian <3
	public void deleteEntityView (EntityView entity) {
		m_toClearEntity.insertAt(0, entity);
	}

	private void clearEntity () {
		IList.Iterator ite = m_toClearEntity.iterator();

		while (ite.hasNext()) {
			m_entityViews.remove(ite.next());
		}
		m_toClearEntity = new LinkedList();
	}

	public void createLightSourceView (ILightSource s) {
		m_light.addLightSource(s);
	}

	public void createMapView (Case[][] cases) {
		m_map = new MapView(cases);
	}

	// Permet d'associer le viewport à la caméra du model
	public Viewport getViewport () {
		return this.vp;
	}

	// Met à jour les éléments de la view en fonction de ceux du model
	private void updateView () {

		// Mise à jour de la liste des entités
		LinkedList.Iterator it = m_entityViews.iterator();

		while (it.hasNext()) {
			((EntityView) it.next()).update(this.vp);
		}

		// Mise à jour des sources de lumière
		it = m_light.getLightSources().iterator();

		while (it.hasNext()) {
			((LightSourceView) it.next()).update(this.vp);
		}

		// Mise à jour de la map
		m_map.setPosition(vp.toLocalX(0), vp.toLocalY(0), vp.getScale());

		clearEntity();
	}

	/*
	 * On met à jour tous les élément de la view pour les afficher au bon endroit.
	 * Ensuite on affiche d'abord la map, puis les entitées puis on applique de le
	 * champ de vision donné par la lumière
	 */
	@Override
	public void paint (Graphics g) {
		this.updateView();
		m_map.paint(g);

		LinkedList.Iterator it = m_entityViews.iterator();
		//int i = 0;

		while (it.hasNext()) {
			EntityView e = (EntityView) it.next();

			// Cette condition permet de ne pas peindre les entités qui ne sont pas dans le viewport.
			// Si les entités sont dans le viewport on teste si elle apparaissent dans un champ lumineux.
			// Si ce n'est pas le cas, on ne les dessine pas non plus.
			if (vp.isInside(e.x, e.y, e.getW(), e.getH()) && m_light.isInside(e.x, e.y)) {
				//i++;
				e.paint(g);
			}
			Entity entity = ((Entity) e.entity);

			g.setColor(Color.red);
			g.drawRect(e.x - (int) (e.getW() * e.scale / 2), e.y - (int) (e.getH() * e.scale / 2), (int) (e.getW() * e.scale), (int) (e.getH() * e.scale));

			if (entity.m_hitbox != null) {
				entity.m_hitbox.paint(g);
			}

		}

		// Applique un masque pour couvrir les zones non éclairées.
		m_light.paint(g);
		m_ath.paint(g);
	}

}
