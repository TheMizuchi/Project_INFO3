package model.entity;

import controller.RefAutomata;
import edu.polytech.oop.collections.ICollection;
import edu.polytech.oop.collections.LinkedList;
import model.Model;
import model.map.TileType;
import view.graphicEntity.EntityView;


public abstract class Entity implements EntityInterface {

	public int m_ID;
	protected int m_pv;
	protected Hitbox m_hitbox;
	EntityProperties m_entityProperties;
	protected RefAutomata m_automata;
	protected EntityView m_ev;
	static final double rangeDetection = 10;
	protected static double ENTITY_MAX_SPEED = 2; // vitesse par seconde
	protected Vector m_vecDir = new Vector();

	protected LinkedList m_blockInterdit;
	protected boolean m_tangible;

	// Liste d'items


	public Entity (double x, double y, EntityProperties ep) {
		m_entityProperties = ep;
		m_ID = ep.getID();
		m_pv = ep.getInitialPv();
		m_hitbox = new Hitbox(x, y, 0.5, 0.5, this);
		m_automata = new RefAutomata(this);
		m_blockInterdit = new LinkedList();
		m_blockInterdit.insertAt(0, TileType.WALL);
		m_tangible = true;
	}

	public static Entity createEntity (double x, double y, EntityProperties entityProperties) {
		Entity e = null;

		switch (entityProperties) {
			case COWBOY:
				e = new Cowboy(x, y);
				break;
			case J1:
				e = J1.getInstance(x, y);
				break;
			case J2:
				e = J2.getInstance(x, y);
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
			case ARCHER:
				e = new Archer(x, y);
				break;
			case TORCH:
				e = Torch.getInstance(x, y);
				break;
			case DOGE:
				e = new Doge(x, y);
				break;
			case MYSTERY:
				e = new MysteryMachine(x, y);
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
		return m_hitbox.getCenterX();
	}

	public double getPosY () {
		return m_hitbox.getCenterY();
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
	public boolean cell (Vector vect, EntityType type) {

		float x = 0, y = 0;
		double ang = vect.getAngle();

		if (ang >= 7 * Math.PI / 4) {
			x = 1;
			y = 0;
		}

		if (ang < 7 * Math.PI / 4) {
			x = 0;
			y = 1;
		}

		if (ang < 5 * Math.PI / 4) {
			x = -1;
			y = 0;
		}

		if (ang < 3 * Math.PI / 4) {
			x = 0;
			y = -1;
		}

		if (ang < Math.PI / 4) {
			x = 1;
			y = 0;
		}

		if (m_hitbox.deplacementValide(getPosX() + x, getPosY() + y))
			return true;

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
		// TODO
		// à implémenter lorsque les directions de dova et diego sont stables
		//if (e_min.position in range of orientation)
		//	return true;
		return false;
	}

	public Entity closest (EntityType type) {
		ICollection.Iterator iter = Model.getlistEntity().iterator();
		Entity e, e_min = null;
		double distMin = Double.MAX_VALUE;

		while (iter.hasNext()) {
			e = (Entity) iter.next();

			if (e.getType() == type) {
				double dist = distance(e);

				if (distMin > dist) {
					e_min = e;
					distMin = dist;
				}

			}
		}
		return e_min;
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
	public void turn (double orientation, boolean absolute) {
		m_vecDir.setAngle((absolute) ? (orientation) : (m_vecDir.getAngle() + orientation));
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
	public void pick () {
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
		Model m;
		m = Model.getInstance();
		Entity e = m.createEntity(m_vecDir.getX(), m_vecDir.getY(), this.m_entityProperties);
	}

	public double distance (Entity e) {
		Hitbox h1 = this.m_hitbox;
		Hitbox h2 = e.m_hitbox;
		double x = h1.getCenterX() - h2.getCenterX();
		double y = h1.getCenterY() - h2.getCenterY();
		return Math.sqrt(x * x + y * y);
	}

	public EntityType getType () {
		return m_entityProperties.getEntityType();
	}

	public int getID () {
		return m_entityProperties.getID();
	}

	public LinkedList getTuileInterdite () {
		return m_blockInterdit;
	}

	public boolean isTanguible () {
		return m_tangible;
	}

	Hitbox getHibox () {
		return m_hitbox;
	}

	public double angleVers (Entity e) {
		double dist = distance(e);
		double truc = m_hitbox.getCenterX() - e.m_hitbox.getCenterX();
		double bidule = m_hitbox.getCenterY() - e.m_hitbox.getCenterY();

		// bas droite
		if (m_hitbox.getCenterX() <= e.m_hitbox.getCenterX() && m_hitbox.getCenterY() >= e.m_hitbox.getCenterY()) {
			return Math.acos(Math.abs(truc) / dist);
		}
		// bas gauche
		else if (m_hitbox.getCenterX() >= e.m_hitbox.getCenterX() && m_hitbox.getCenterY() >= e.m_hitbox.getCenterY()) {
			return Math.acos(Math.abs(bidule) / dist) + Math.PI / 2;
		}

		// haut gauche
		else if (m_hitbox.getCenterX() >= e.m_hitbox.getCenterX() && m_hitbox.getCenterY() <= e.m_hitbox.getCenterY()) {
			return Math.acos(Math.abs(truc) / dist) + Math.PI ;
		}

		// haut droite
		else if (m_hitbox.getCenterX() <= e.m_hitbox.getCenterX() && m_hitbox.getCenterY() <= e.m_hitbox.getCenterY()) {
			return Math.acos(Math.abs(bidule) / dist) + Math.PI / 2 * 3;
		}

		/*
		 * System.out.println(e.m_hitbox.getCenterX());
		 * System.out.println(m_hitbox.getCenterX()); System.out.println(truc);
		 * 
		 * System.out.println(dist);
		 */

		throw new RuntimeException("erreur lors du calcul d'angle de ciblage");
	}
}
