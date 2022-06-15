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

	public static final int COWBOY_ID = 0;
	public static final int J1_ID = 1;
	public static final int J2_ID = 2;
	public static final int BLOON_ID = 3;
	public static final int ZOMBIE_ID = 4;
	public static final int BAT_ID = 5;
	public static final int DART_MONKEY_ID = 6;

	public static final int ENTITY_NUMBER = 7;

	private int m_time_passed;
	private LinkedList m_listeEntity;
	private LinkedList m_listeLight;
	private static Model m_instance = null;
	private Controller m_cont;
	private MyCanvas m_canvas;
	private World m_w;
	private Map m_map;


	private Model () {
		m_listeEntity = new LinkedList();
		m_listeLight = new LinkedList();
		m_cont = Controller.getInstance();
		m_canvas = MyCanvas.getInstance();
		createMap();
	}

	public static Model getInstance () {
		if (m_instance == null)
			m_instance = new Model();
		return m_instance;
	}

	public void update (long elapsed) {
		m_time_passed += elapsed;


		if (m_time_passed > 100) {
			m_cont.transfertTab();
			
			Iterator it = m_listeEntity.iterator();

			while (it.hasNext()) {
				Entity entity = (Entity) it.next();
				entity.update(m_time_passed);
			}
			it = m_listeLight.iterator();
			m_time_passed -= 100;

			while (it.hasNext()) {
				LightSource ls = (LightSource) it.next();
				ls.update();
			}
		}
	}

	public Entity createEntity () {
		Entity e = new Entity(1, 1, 0);
		m_listeEntity.insertAt(0, e);
		return e;
	}
	
	public void createLightSource(Entity e) {
		m_listeLight.insertAt(0, new LightSource(0, 0, 5, e));
	}
	
	public void createMap() {
		try {
			m_w = new World("resources/rooms.json");
		}
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m_map = new Map(m_w, 160, 120, 1);
		MyCanvas.getInstance().createMapView(m_map.getCases());
	}

}
