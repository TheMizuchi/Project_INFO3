package model.entity;

import controller.RefAutomata;
import edu.polytech.oop.collections.LinkedList;
import model.entity.behavior.EntityBehavior;
import model.map.TileType;
import view.graphicEntity.EntityView;


public abstract class Entity implements EntityInterface {

	public int m_ID;
	protected int m_pv;
	public Hitbox m_hitbox;
	EntityProperties m_entityProperties;
	protected RefAutomata m_automata;
	protected EntityView m_ev;
	static final double rangeDetection = 10;
	protected static double ENTITY_MAX_SPEED = 2; // vitesse par seconde
	protected Vector m_vecDir = new Vector();

	private static int m_count = 0;
	private int m_c;

	protected LinkedList m_blockInterdit;
	protected boolean m_tangible;
	protected EntityBehavior eb;

	// Liste d'items


	public Entity (double x, double y, EntityProperties ep) {
		m_entityProperties = ep;
		m_ID = ep.getID();
		m_pv = ep.getInitialPv();
		m_hitbox = new Hitbox(x, y, 0.5, 0.5, this, false);
		m_automata = new RefAutomata(this);
		m_blockInterdit = new LinkedList();
		m_blockInterdit.insertAt(0, TileType.WALL);
		m_tangible = true;
		m_c = m_count;
		m_count++;
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
			case BLOON_BOSS:
				e = new Bloon(x, y);
				((Bloon) e).setLevel(5);
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

	public boolean getPossessable () {
		return m_entityProperties.getPossessable();
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
			return Math.acos(Math.abs(truc) / dist) + Math.PI;
		}

		// haut droite
		else if (m_hitbox.getCenterX() <= e.m_hitbox.getCenterX() && m_hitbox.getCenterY() <= e.m_hitbox.getCenterY()) {
			return Math.acos(Math.abs(bidule) / dist) + Math.PI / 2 * 3;
		}

		throw new RuntimeException("erreur lors du calcul d'angle de ciblage");
	}

	public boolean equal (Entity e) {

		if (e != null) {
			boolean bool = e.m_c == m_c;
			return bool;
		}
		return false;
	}
	//miam miam

	//Coucou petit développeur

	void attack (Entity cible) {
		this.eb.attack(cible);
	}

	void interact () {
		this.eb.interact();
	}

	@Override
	public boolean myDir (double orientation, boolean absolute) {
		return this.eb.myDir(orientation, absolute, m_vecDir);
	}

	@Override
	public boolean cell (Vector vect, EntityType type) {
		return this.eb.cell(vect, type, m_hitbox);
	}

	@Override
	public boolean closest (Direction orientation, EntityType type) {
		return this.eb.closest(orientation, type, rangeDetection);
	}

	public Entity closest (EntityType type) {
		return this.eb.closest(type);
	}

	public Entity closest (boolean possessable) {
		return this.eb.closest(possessable);
	}

	@Override
	public boolean gotPower () {
		return this.eb.gotPower(m_pv);
	}

	@Override
	public boolean gotStuff () {
		return this.eb.gotStuff();
	}

	@Override
	public void pop () {
		this.eb.pop();
	}

	@Override
	public void wizz () {
		this.eb.wizz();
	}

	@Override
	public void move (Direction orientation) {

		if (this.eb.move(orientation, m_vecDir)) {
			m_ev.walk();
		}
	}

	@Override
	public void turn (double orientation, boolean absolute) {
		this.eb.turn(orientation, absolute, m_vecDir);
	}

	@Override
	public void hit (Vector vec) {
		this.eb.hit(vec);
	}

	@Override
	public void protect (Direction orientation) {
		this.eb.protect(orientation);

	}

	@Override
	public void pick () {
		this.eb.pick();
	}

	@Override
	public void put (Direction orientation) {
		this.eb.put(orientation);
	}

	@Override
	public void store () {
		this.eb.store();
	}

	@Override
	public void get () {
		this.eb.get();
	}

	@Override
	public void power () {
		this.eb.power();
	}

	@Override
	public void explode () {
		this.eb.explode();
	}

	@Override
	public void egg (double orientationx, double orientationy) {
		this.eb.egg(orientationx, orientationy, m_hitbox, m_entityProperties);
	}
}
