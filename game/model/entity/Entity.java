package model.entity;

import controller.RefAutomata;
import edu.polytech.oop.collections.ICollection;
import edu.polytech.oop.collections.LinkedList;
import model.Model;
import model.entity.behavior.EntityBehavior;
import model.map.TileType;
import view.MyCanvas;
import view.graphicEntity.EntityView;


public abstract class Entity implements EntityInterface {

	public int m_ID;
	protected int m_pv;
	public Hitbox m_hitbox;
	EntityProperties m_entityProperties;
	protected RefAutomata m_automata;
	protected EntityView m_ev;
	protected static double DETECTIONRANGE = 10;
	protected double EntityMaxSpeed = 2; // vitesse par seconde
	protected static double MobMaxSpeed = 1;
	protected static double ENTITY_MAX_ACCELERATION = 3;
	protected static int ENTITY_ATTACK_CD = 500;
	protected Vector m_vecDir = new Vector();

	private static int m_count = 0;
	private int m_c;

	protected LinkedList m_blockInterdit;
	protected boolean m_tangible;
	public EntityBehavior m_eb;

	protected int m_nbDamages;
	protected double cdAction;
	protected double cdDmgTaken;
	protected static double TEMPS_INVUNERABILITE = 3000;

	// Liste d'items


	public Entity (double x, double y, EntityProperties ep) {
		m_entityProperties = ep;
		m_ID = ep.getID();
		m_pv = ep.getInitialPv();
		m_nbDamages = ep.getDamages();
		m_hitbox = new Hitbox(x, y, ep.getWidth(), ep.getHeight(), this);
		m_automata = new RefAutomata(this);
		m_blockInterdit = new LinkedList();
		m_tangible = true;
		m_c = m_count;
		m_count++;
		m_blockInterdit.insertAt(0, TileType.WALL);
		m_blockInterdit.insertAt(1, TileType.VOID);
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
				e = new Bloon(x, y, 2);
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
				e = new Bloon(x, y, 5);
				break;
			case DOOR:
				e = new Door(x, y);
				break;
			case BATSPAWNER:
				e = new BatSpawner(x, y);
				break;
			case KEY:
				e = Key.getInstance(x, y);
				break;
			case STAIRS:
				e = new Stairs(x, y);
				break;
			case ARROW:
				e = new Arrow(x, y);
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

	public void setTangible (boolean b) {
		m_tangible = b;
	}

	public void update (long elapsed) {
		// déplacement
		m_automata.step();
		double speedX = m_vecDir.getX() * EntityMaxSpeed;
		double speedY = m_vecDir.getY() * EntityMaxSpeed;
		m_hitbox.move(speedX * elapsed / 1000, speedY * elapsed / 1000);
	}

	void attack (Entity cible) {

		if (!isDeath()) {
			cible.takeDamages(m_nbDamages);
		}
	}

	public boolean isDeath () {
		return m_pv <= 0;
	}

	public double distance (Entity e) {
		Hitbox h1 = m_hitbox;
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

	public Hitbox getHibox () {
		return m_hitbox;
	}

	public double getAngle () {
		return m_vecDir.getAngle();
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
		else if (m_hitbox.getCenterX() <= e.m_hitbox.getCenterX() && m_hitbox.getCenterY() <= e.m_hitbox.getCenterY())

		{
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

	void interact () {
		m_eb.interact();
	}


	// Permet de choisir la précision que vous voulez sur l'angle de MyDir
	static final double MYDIR_SENSI = 15 * 180 / Math.PI;


	@Override
	public boolean myDir (double orientation, boolean absolute) {
		return m_eb.myDir(orientation, absolute);
	}

	@Override
	public boolean cell (Vector vect, EntityType type) {
		return m_eb.cell(vect, type);
	}

	public double getRangeDetection () {
		return DETECTIONRANGE;
	}

	// La direction est inutilisée dans le jeu
	@Override
	public boolean closest (double orientation, EntityType type) {
		return m_eb.closest(orientation, type);
	}

	public Entity closest (EntityType type) {
		ICollection.Iterator iter = Model.getlistEntity().iterator();
		Entity e, e_min = null;
		double distMin = Double.MAX_VALUE;

		while (iter.hasNext()) {
			e = (Entity) iter.next();

			if (e.getType() == type) {
				double dist = this.distance(e);

				if (distMin > dist) {
					e_min = e;
					distMin = dist;
				}

			}
		}
		return e_min;
	}

	public Entity closest (boolean possessable) {
		ICollection.Iterator iter = Model.getlistEntity().iterator();
		Entity e, e_min = null;
		double distMin = Double.MAX_VALUE;

		while (iter.hasNext()) {
			e = (Entity) iter.next();

			if (e.getPossessable() == possessable) {
				double dist = this.distance(e);

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
		return m_eb.gotPower();
	}

	@Override
	public boolean gotStuff () {
		return m_eb.gotStuff();
	}

	@Override
	public void pop () {
		m_eb.pop();
	}

	@Override
	public void wizz () {
		m_eb.wizz();
	}

	@Override
	public void move (Direction orientation) {

		if (m_eb.move(orientation, m_vecDir)) {
			m_ev.walk();
		}
	}

	@Override
	public void turn (double orientation, boolean absolute) {
		m_eb.turn(orientation, absolute, m_vecDir);
	}

	// les hits des joueurs sont override dans la classe player
	@Override
	public void hit (Vector vec) {
		m_eb.hit(vec);
	}

	@Override
	public void protect (Direction orientation) {
		m_eb.protect(orientation);

	}

	@Override
	public void pick () {
		m_eb.pick();
	}

	@Override
	public void put (Direction orientation) {
		m_eb.put(orientation);
	}

	@Override
	public void store () {
		m_eb.store();
	}

	@Override
	public void get () {
		m_eb.get();
	}

	@Override
	public void power () {
		m_eb.power();
	}

	@Override
	public void explode () {
		m_eb.explode();
	}

	@Override
	public void egg (double orientationx, double orientationy) {
		m_eb.egg(orientationx, orientationy, m_hitbox, m_entityProperties);
	}

	public void deleteEntity () {
		Model.getInstance().deleteEntity(this);
		MyCanvas.getInstance().deleteEntityView(m_ev);
	}

	public EntityProperties getProperties () {
		return m_entityProperties;
	}

	public int getPv () {
		return m_pv;
	}

	public void setPv (int pv) {
		m_pv = pv;
	}

	public Hitbox getHitbox () {
		return m_hitbox;
	}

	public double getMobSpeed () {
		return MobMaxSpeed;
	}

	void takeDamages (int damages) {

		if (cdDmgTaken >= 0)
			return;

		cdDmgTaken = TEMPS_INVUNERABILITE;

		m_pv -= damages;

		if (m_pv < 0) {
			m_pv = 0;
		}

		this.m_automata.step();

		if (isDeath()) {
			deleteEntity();
		}
	}

	int getDamages () {
		return m_nbDamages;
	}

	public boolean isBloon () {
		if (getProperties() == EntityProperties.BLOON || getProperties() == EntityProperties.BLOON_BOSS)
			return true;
		return false;
	}

	public boolean isDoor () {
		if (getProperties() == EntityProperties.DOOR)
			return true;
		return false;
	}

	public void waitt (long time) {
		m_automata.waitt(time);
	}

	public int getNbDamages () {
		return m_nbDamages;
	}
}
