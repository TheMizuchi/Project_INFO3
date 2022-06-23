package model;

import controller.Controller;
import edu.polytech.oop.collections.ArrayList;
import edu.polytech.oop.collections.IList;
import edu.polytech.oop.collections.LinkedList;
import edu.polytech.oop.collections.LinkedList.Iterator;
import model.entity.Entity;
import model.entity.EntityProperties;
import model.entity.EntityType;
import model.map.Map;
import model.map.generator.Graph;
import model.map.generator.JsonDecode;
import model.map.generator.Node;
import model.map.generator.Room;
import view.MyCanvas;


public class Model {

	// Référence MVC
	private static Model m_instance = null;
	private Controller m_cont;
	private MyCanvas m_canvas;

	// Variables locales
	private static LinkedList m_listeEntity;
	private LinkedList m_listeLight;
	//<<<<<<< HEAD
	static public Camera m_cam;
	//	private Map m_map;
	//=======
	//	private Camera m_cam;
	static private Map m_map;
	//>>>>>>> master
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

	//Constructeur pour TestWorld
	public Model (Object o) {
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
		createMap();

	}

	//méthode tmp pour les tests
	private void loadEnv (Room spawnRoom) {
		m_cam = Camera.getInstance(m_canvas.getViewport(), m_map.getWidth() / 2, m_map.getHeight() / 2);
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
			if (entity.getType() == EntityType.ENEMY)
				entity.update(elapsed);
			else
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

	public void deleteEntity (Entity e) {
		// TODO
	}

	public void createLightSource (Entity e) {
		m_listeLight.insertAt(0, new LightSource(0, 0, 5, e));
	}

	public Room createMap () {
		boolean correctG = false;
		Graph MST = null;
		Graph g = null;
		int nb_gen = 0;

		while (!correctG) {
			m_map = new Map(this, 1, 15);
			nb_gen++;
			g = new Graph(rooms);
			g.delaunay();
			correctG = true;

			IList.Iterator iterNode = g.ListNode.iterator();

			while (iterNode.hasNext()) {
				Node n = (Node) iterNode.next();

				if (n.getListArc().length() < 2) {
					correctG = false;
					IList.Iterator iterRoom = rooms.iterator();

					while (iterRoom.hasNext()) {
						Room r = (Room) iterRoom.next();
						r.setUpperLeft(-1, -1);
					}
					break;
				}
			}

			if (correctG) {
				MST = g.min_spanning_tree();
				MST.add_random_arc(g);
				iterNode = MST.ListNode.iterator();

				while (iterNode.hasNext()) {
					Node n = (Node) iterNode.next();

					if (n.getListArc().length() < 1) {
						correctG = false;
						IList.Iterator iterRoom = rooms.iterator();

						while (iterRoom.hasNext()) {
							Room r = (Room) iterRoom.next();
							r.setUpperLeft(-1, -1);
						}
						break;
					}
				}
			}
		}
		//System.out.println("nb generate = " + nb_gen);
		m_map.corridors(MST);

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
