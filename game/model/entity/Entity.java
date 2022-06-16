package model.entity;

import controller.RefAutomata;
import view.MyCanvas;
import view.graphicEntity.CowboyView;


public class Entity implements EntityInterface {

	public int m_ID;
	private Hitbox m_hitbox;
	protected RefAutomata m_automata;
	private CowboyView m_cv;

	private static final double ENTITY_MAX_SPEED = 2; // vitesse par seconde
	private Vector m_vecDir = new Vector();

	// Liste d'items


	public Entity (double x, double y, int ID) {
		m_ID = ID;
		m_hitbox = new Hitbox(x, y, 0.5, 0.5);
		m_automata = new RefAutomata(this);

		this.m_cv = new CowboyView(this);
		MyCanvas.getInstance().createEntityView(this.m_cv);
	}

	public boolean getOrientation () {
		// T si gauche / north
		return m_vecDir.getX() < 0;
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
		this.m_cv.spin();
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
		dir.move(m_vecDir);
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

}
