package model.entity;

import controller.RefAutomata;
import edu.polytech.oop.collections.ICollection;
import model.Model;
import view.MyCanvas;
import view.graphicEntity.CowboyView;
import view.graphicEntity.EntityView;


public class Entity implements EntityInterface {

	public int m_ID;
	protected Hitbox m_hitbox;
	TypeEntity type;
	protected RefAutomata m_automata;
	protected EntityView m_ev;

	static final double rangeDetection = 10;
	private static final double ENTITY_MAX_SPEED = 2; // vitesse par seconde
	private Vector m_vecDir = new Vector();

	// Liste d'items


	public Entity (double x, double y, int ID) {
		m_ID = ID;
		m_hitbox = new Hitbox(x, y, 0.5, 0.5);
		m_automata = new RefAutomata(this);
		type = new TypeEntity(ID);
	}

	public static Entity createEntity (int x, int y, int ID) {
		Entity e = null;

		switch (ID) {
			case Model.COWBOY_ID:
				e = new Cowboy(x, y);
				break;
			case Model.J1_ID:
				e = new J1(x, y);
				break;
			case Model.J2_ID:
				e = new J2(x, y);
				break;
			case Model.BLOON_ID:
				e = new Bloon(x, y);
				break;
			case Model.SKELETON_ID:
				e = new Skeleton(x, y);
				break;
			case Model.BAT_ID:
				e = new Bat(x, y);
				break;
			case Model.DART_MONKEY_ID:
				e = new DartMonkey(x, y);
				break;
			case Model.TORCH_ID:
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
	public boolean cell (Direction orientation, TypeEntity type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean closest (Direction orientation, TypeEntity type) {
		ICollection.Iterator iter = Model.getlistEntity().iterator();
		Entity e, e_min;
		double distMin = Float.MAX_VALUE;

		while (iter.hasNext()) {
			e = (Entity) iter.next();

			if (e.type.getType() == type.getType()) {
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
		// TODO Auto-generated method stub
		return false;
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

	public int getType () {
		return type.getType();
	}
}
