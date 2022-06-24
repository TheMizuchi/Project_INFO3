package model;

import controller.Controller;
import edu.polytech.oop.collections.ArrayList;
import edu.polytech.oop.collections.IList;
import edu.polytech.oop.collections.LinkedList;
import edu.polytech.oop.collections.LinkedList.Iterator;
import model.entity.Entity;
import model.entity.EntityProperties;
import model.entity.J1;
import model.entity.J2;
import model.entity.Key;
import model.entity.Torch;
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
	public static Camera m_cam;
	private static Map m_map;
	private ArrayList m_rooms; //Totalité des salles pour pouvoir piocher dedans
	private JsonDecode m_jd;
	private int m_level;


	public Model () {
		String jsonPath = "resources/rooms.json";
		m_jd = new JsonDecode(this, jsonPath);
		m_rooms = new ArrayList();

		for (int i = 0; i < m_jd.getNbRooms(); i++) {
			m_rooms.insertAt(m_rooms.length(), m_jd.newRoom(i));
		}
		m_listeEntity = new LinkedList();
		m_listeLight = new LinkedList();
		m_cont = Controller.getInstance();
		m_canvas = MyCanvas.getInstance();
		m_level = 0;
	}

	//Constructeur pour TestWorld
	public Model (Object o) {
		String jsonPath = "resources/rooms.json";
		m_jd = new JsonDecode(this, jsonPath);
		m_rooms = new ArrayList();

		for (int i = 0; i < m_jd.getNbRooms(); i++) {
			m_rooms.insertAt(m_rooms.length(), m_jd.newRoom(i));
		}
		m_listeLight = new LinkedList();
		m_cont = Controller.getInstance();
		m_canvas = MyCanvas.getInstance();
		createMap(1, 30);

	}

	//méthode tmp pour les tests
	public void loadEnv () {
		m_cam = Camera.getInstance(m_canvas.getViewport(), m_map.getWidth() / 2, m_map.getHeight() / 2);
		Room spawnRoom = m_map.getSpawn();
		Room keyRoom = m_map.getKey();
		spawnRoom.spawnEntities(m_map, 0);
		keyRoom.spawnEntities(m_map, 0);
		m_map.doors();
	}

	public void newLevel () {
		if (m_level < 0)
			throw new RuntimeException("Invalid level");

		if (m_level >= 0 && m_level <= 2) {

			if (m_listeEntity == null || m_listeEntity.length() == 0) {
				m_listeEntity = new LinkedList();
				m_level++;
				int nbRooms = 10 + 3 * m_level;
				createMap(m_level, nbRooms);
				loadEnv();
			} else {
				J1 player1 = J1.getInstance();
				J2 player2 = J2.getInstance();
				Key cle = Key.getInstance();
				Torch tor = Torch.getInstance();
				
				Iterator iterEntity = m_listeEntity.iterator();
				while(iterEntity.hasNext()) {
					Entity e = (Entity) iterEntity.next();
					if(!e.equal(player1) && !e.equal(player2) && !e.equal(cle) && !e.equal(tor)) {
						e.deleteEntity();
					}
				}				
				m_level++;
				int nbRooms = 10 + 3 * m_level;
				createMap(m_level, nbRooms);
				loadEnv();
			}

		}

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

	public void deleteEntity (Entity e) {
		m_listeEntity.remove(e);
	}

	public void createLightSource (Entity e) {
		m_listeLight.insertAt(0, new LightSource(0, 0, 5, e));
	}

	public Room createMap (int level, int nbRooms) {
		boolean correctG = false;
		Graph MST = null;
		Graph g = null;
		int nb_gen = 0;

		while (!correctG) {
			m_map = new Map(this, level, nbRooms);
			nb_gen++;
			g = new Graph(m_rooms);
			g.delaunay();
			correctG = true;

			IList.Iterator iterNode = g.ListNode.iterator();

			while (iterNode.hasNext()) {
				Node n = (Node) iterNode.next();

				if (n.getListArc().length() < 2) {
					correctG = false;
					IList.Iterator iterRoom = m_rooms.iterator();

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
						IList.Iterator iterRoom = m_rooms.iterator();

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
		return this.m_rooms;
	}

	public static IList getlistEntity () {
		return m_listeEntity;
	}

	public static Map getMap () {
		return m_map;
	}

	public Entity getJ (int i) {
		IList.Iterator iter = m_listeEntity.iterator();
		EntityProperties ep;

		switch (i) {
			case 1:
				ep = EntityProperties.J1;
				break;
			case 2:
				ep = EntityProperties.J2;
				break;
			default:
				throw new RuntimeException("Unknown player");
		}

		while (iter.hasNext()) {
			Entity e = (Entity) iter.next();
			if (e.getProperties() == ep)
				return e;
		}
		return null;
	}

}
