package model;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import controller.Controller;
import edu.polytech.oop.collections.*;
import edu.polytech.oop.collections.LinkedList.Iterator;
import model.entity.Entity;
import model.map.Map;
import model.map.World;
import view.MyCanvas;


public class Model {

	// Constante pour définir les ID des entités
	public static final int COWBOY_ID = 0;
	public static final int J1_ID = 1;
	public static final int J2_ID = 2;
	public static final int BLOON_ID = 3;
	public static final int ZOMBIE_ID = 4;
	public static final int BAT_ID = 5;
	public static final int DART_MONKEY_ID = 6;

	public static final int ENTITY_NUMBER = 7;

	// Référence MVC
	private static Model m_instance = null;
	private Controller m_cont;
	private MyCanvas m_canvas;

	// Variables locales
	private int m_time_passed;
	private LinkedList m_listeEntity;
	private LinkedList m_listeLight;
	private Camera m_cam;
	private World m_w;
	private Map m_map;


	private Model () {
		m_listeEntity = new LinkedList();
		m_listeLight = new LinkedList();
		m_cont = Controller.getInstance();
		m_canvas = MyCanvas.getInstance();
		createMap();
		loadEnv();
	}
	
	//méthode tmp pour les tests
	private void loadEnv() {
		Entity j1 = createEntity(5, 0, 0);
		createLightSource(j1);
		Entity j2 = createEntity(-5, 0, 0);
		createLightSource(j2);
		m_cam = new Camera(m_canvas.getViewport(), j1, j2);
	}

	public static Model getInstance () {
		if (m_instance == null)
			m_instance = new Model();
		return m_instance;
	}

	public void update (long elapsed) {

		m_cont.transfertTab();

		Iterator it = m_listeEntity.iterator();

		while (it.hasNext()) {
			Entity entity = (Entity) it.next();
			entity.update(elapsed);
			m_cam.setPosition(entity.getPosX(), entity.getPosY(), 1);
		}

		it = m_listeLight.iterator();

		while (it.hasNext()) {
			LightSource ls = (LightSource) it.next();
			ls.update();
		}
		
		m_cam.update();
	}

	public Entity createEntity (int x, int y, int ID) {
		Entity e = Entity.createEntity(x, y, ID);
		m_listeEntity.insertAt(0, e);
		return e;
	}

	public void createLightSource (Entity e) {
		m_listeLight.insertAt(0, new LightSource(0, 0, 5, e));
	}

	public void createMap () {

		try {
			m_w = new World("resources/rooms.json");
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		m_map = new Map(m_w, 1, 100);
		m_canvas.createMapView(m_map.getCases());
	}

}
