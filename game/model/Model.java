package model;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import controller.Controller;
import edu.polytech.oop.collections.*;
import edu.polytech.oop.collections.LinkedList.Iterator;
import model.entity.Entity;
import model.map.Map;
import model.map.generator.JsonDecode;
import model.map.generator.Room;
import view.MyCanvas;


public class Model {

	// Constante pour définir les ID des entités
	public static final int COWBOY_ID = 0;
	public static final int J1_ID = 1;
	public static final int J2_ID = 2;
	public static final int TORCH_ID = 3;
	public static final int SKELETON_ID = 4;
	public static final int BAT_ID = 5;
	public static final int DART_MONKEY_ID = 6;
	public static final int BLOON_ID = 7;

	public static final int ENTITY_NUMBER = 8;

	// Référence MVC
	private static Model m_instance = null;
	private Controller m_cont;
	private MyCanvas m_canvas;

	// Variables localesq
	private int m_time_passed;
	private static LinkedList m_listeEntity;
	private LinkedList m_listeLight;
	private Camera m_cam;
	private Map m_map;
	private ArrayList rooms; //Totalité des salles pour pouvoir piocher dedans
	private JsonDecode jd;


	private Model () throws ParseException, IOException {
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

	public static Model getInstance () throws ParseException, IOException {
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

	public Entity createEntity (int x, int y, int ID) {
		Entity e = Entity.createEntity(x, y, ID);
		m_listeEntity.insertAt(m_listeEntity.length(), e);

		if (ID == J1_ID) {
			m_cam.setj1(e);
		} else if (ID == J2_ID)
			;

		{
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

}
