package model;

import controller.Controller;
import edu.polytech.oop.collections.ArrayList;
import edu.polytech.oop.collections.IList;
import edu.polytech.oop.collections.LinkedList;
import edu.polytech.oop.collections.LinkedList.Iterator;
import model.entity.Entity;
import model.entity.EntityProperties;
import model.map.Map;
import model.map.generator.JsonDecode;
import model.map.generator.Room;
import view.MyCanvas;


public class Model {

	// Référence MVC
	private static Model m_instance = null;
	private Controller m_cont;
	private MyCanvas m_canvas;

	// Variables locales
	private int m_time_passed;
	private static LinkedList m_listeEntity;
	private LinkedList m_listeLight;
	private Camera m_cam;
	private static Map m_map;
	private ArrayList rooms; //Totalité des salles pour pouvoir piocher dedans
	private JsonDecode jd;


	public Model () {
		String jsonPath = "resources/rooms.json";
		jd = new JsonDecode(this, jsonPath);
		rooms = new ArrayList();

		for (int i = 0; i < jd.getNbRooms(); i++) {
			rooms.insertAt(rooms.length(), jd.newRoom(i));
		}
		m_listeEntity = new LinkedList();
		m_listeLight = new LinkedList();
		m_cont = Controller.getInstance();
		m_canvas = MyCanvas.getInstance();
		Room spawnRoom = createMap();
		loadEnv(spawnRoom);

	}

	//méthode tmp pour les tests
	private void loadEnv (Room spawnRoom) {
		m_cam = new Camera(m_canvas.getViewport());
		spawnRoom.spawnEntities(m_map);

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
			m_cam.update();

		}

		it = m_listeLight.iterator();

		while (it.hasNext()) {
			LightSource ls = (LightSource) it.next();
			ls.update();
		}

		m_cam.update();
	}

	public Entity createEntity (double x, double y, EntityProperties entityProperties) {
		Entity e = Entity.createEntity(x, y, entityProperties);
		m_listeEntity.insertAt(m_listeEntity.length(), e);

		if (entityProperties == EntityProperties.J1) {
			m_cam.setj1(e);
		} else if (entityProperties == EntityProperties.J2) {
			m_cam.setj2(e);
		}
		return e;
	}

	public void createLightSource (Entity e) {
		m_listeLight.insertAt(0, new LightSource(0, 0, 5, e));
	}

	public Room createMap () {
		m_map = new Map(this, 1, 100);
		m_canvas.createMapView(m_map.getCases());
		return m_map.getSpawn();
	}

	public ArrayList getRooms () {
		return this.rooms;
	}

	public static IList getlistEntity () {
		return m_listeEntity;
	}

	public static Map getMap () {
		return m_map;
	}

}
