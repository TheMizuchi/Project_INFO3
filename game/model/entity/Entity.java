package model.entity;

import controller.RefAutomata;
import edu.polytech.oop.collections.ICollection;
import model.Model;
import model.map.generator.RoomType;
import view.MyCanvas;
import view.graphicEntity.CowboyView;
import view.graphicEntity.EntityView;


public class Entity implements EntityInterface {

	public int m_ID;
	private int m_pv;
	protected Hitbox m_hitbox;
	EntityProperties m_entityProperties;
	protected RefAutomata m_automata;
	protected EntityView m_ev;
	static final double rangeDetection = 10;
	private static final double ENTITY_MAX_SPEED = 2; // vitesse par seconde
	private Vector m_vecDir = new Vector();

	// Liste d'items


	public Entity (double x, double y, EntityProperties ep) {
		m_entityProperties = ep;
		m_ID = ep.getID();
		m_pv = ep.getInitialPv();
		m_hitbox = new Hitbox(x, y, 0.5, 0.5);
		m_automata = new RefAutomata(this);
	}

	public static Entity createEntity (int x, int y, EntityProperties entityProperties) {
		Entity e = null;

		switch (entityProperties) {
			case COWBOY:
				e = new Cowboy(x, y);
				break;
			case J1:
				e = new J1(x, y);
				break;
			case J2:
				e = new J2(x, y);
				break;
			case BLOON:
				e = new Bloon(x, y);
				break;
			case SKELETON:
				e = new Skeleton(x, y);
				break;
			case BAT:
				e = new Bat(x, y);
				break;
			case DART_MONKEY:
				e = new DartMonkey(x, y);
				break;
			case TORCH:
				e = new Torch(x, y);
				break;
			default:
				throw new RuntimeException("Aie Aie Aie ... Ton ID n'existe pas, pauvre de toi");

		}
		return e;
	}

	public int getOrientation () {

		// T si gauche / north
		if (m_vecDir.getX() < 0) {
			return -1;
		} else if (m_vecDir.getX() > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	public Vector getDirVector () {
		return m_vecDir;
	}

	public double getPosX () {
		return m_hitbox.getX();
	}

	public double getPosY () {
		return m_hitbox.getY();
	}

	public void update (long elapsed) {
		// déplacement
		m_automata.step();
		double speedX = m_vecDir.getX() * ENTITY_MAX_SPEED;
		double speedY = m_vecDir.getY() * ENTITY_MAX_SPEED;
		m_hitbox.move(speedX * elapsed / 1000, speedY * elapsed / 1000);
	}

	void attack () {}

	void interact () {}

	@Override
	public boolean myDir (Direction orientation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cell (Direction orientation, EntityType type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean closest (Direction orientation, EntityType type) {
		ICollection.Iterator iter = Model.getlistEntity().iterator();
		Entity e, e_min;
		double distMin = Float.MAX_VALUE;

		while (iter.hasNext()) {
			e = (Entity) iter.next();

			if (e.getType() == type) {
				double dist = distance(e);

				if (distMin > dist && distMin < rangeDetection) {
					e_min = e;
					distMin = dist;
				}

			}
		}
		// à implémenter lorsque les directions de dova et diego sont stables
		//if (e_min.position in range of orientation)
		//	return true;
		return false;

	}

	@Override
	public boolean gotPower () {
		return m_pv > 0;
	}

	@Override
	public boolean gotStuff () {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void pop () {
		// TODO C'est un truc à faire ça 
	}

	@Override
	public void wizz () {
		// TODO Auto-generated method stub

	}

	@Override
	public void waitt () {
		// TODO Auto-generated method stub

	}

	@Override
	public void move (Direction dir) {
		boolean sw = dir.move(m_vecDir);
		if (sw)
			m_ev.walk();
	}

	@Override
	public void rotation (Direction orientation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void hit (Direction orientation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void protect (Direction orientation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pick (Direction orientation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void put (Direction orientation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void store () {
		// TODO Auto-generated method stub

	}

	@Override
	public void get () {
		// TODO Auto-generated method stub

	}

	@Override
	public void power () {
		// TODO Auto-generated method stub

	}

	@Override
	public void explode () {
		// TODO Auto-generated method stub

	}

	@Override
	public void egg (Direction orientation) {
		// TODO Auto-generated method stub

	}

	public double distance (Entity e) {
		Hitbox h1 = this.m_hitbox;
		Hitbox h2 = e.m_hitbox;
		double x = Math.pow(h1.getX(), h1.getX()) - Math.pow(h2.getX(), h2.getX());
		double y = Math.pow(h1.getY(), h1.getY()) - Math.pow(h2.getY(), h2.getY());
		return Math.sqrt(x + y);
	}

	public EntityType getType () {
		return m_entityProperties.getEntityType();
	}

}
