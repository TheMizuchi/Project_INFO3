package model.entity;

import controller.RefAutomata;
import model.Model;
import view.MyCanvas;
import view.graphicEntity.CowboyView;

public class Entity implements EntityInterface {

	public int m_ID;
	private Hitbox m_hitbox;
	Direction m_orientation;
	protected RefAutomata m_automata;
	private CowboyView m_cv;
	private double m_speedX;
	private double m_speedY;

	private static final double ENTITY_MAX_SPEED = 2; // vitesse par seconde
	private static final double ENTITY_MAX_SPEED_DIAGONAL = Math.sqrt(ENTITY_MAX_SPEED)/2; 

	// Liste d'items

	//

	public Entity(double x, double y, int ID) {
		m_ID = ID;
		m_orientation = new Direction();
		m_hitbox = new Hitbox(x, y, 0.5, 0.5);
		m_automata = new RefAutomata(this);

		this.m_cv = new CowboyView(this);
		MyCanvas.getInstance().createEntityView(this.m_cv);
	}

	public boolean getOrientation() {
		// T si gauche / north
		return m_orientation.getDirectionX() < 0 || m_orientation.getDirectionY() < 0;
	}

	public double getPosX() {
		return m_hitbox.getX();
	}

	public double getPosY() {
		return m_hitbox.getY();
	}

	public void update(long elapsed) {
		// déplacement
		m_automata.step();
		this.deplacement(elapsed);
	}

	void attack() {
	}

	void interact() {
	}

	@Override
	public boolean myDir(Direction orientation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gotPower() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gotStuff() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void pop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void wizz() {
		// TODO Auto-generated method stub

	}

	@Override
	public void waitt() {
		// TODO Auto-generated method stub

	}

	@Override
	public void move(Direction orientation) {
		m_orientation.updateDirection(orientation);
	}

	@Override
	public void rotation(Direction orientation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void hit(Direction orientation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void protect(Direction orientation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pick(Direction orientation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void put(Direction orientation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void store() {
		// TODO Auto-generated method stub

	}

	@Override
	public void get() {
		// TODO Auto-generated method stub

	}

	@Override
	public void power() {
		// TODO Auto-generated method stub

	}

	@Override
	public void explode() {
		// TODO Auto-generated method stub

	}

	@Override
	public void egg(Direction orientation) {
		// TODO Auto-generated method stub

	}
	
	
	private void deplacement(long elapse) {
		double d = 50 * elapse / 1000 * elapse / 1000;
		double angle = m_orientation.toAngle() ; 
        double sx = m_speedX + d * Math.cos(angle);
        double sy = m_speedY + d * Math.sin(angle);
        if (sx * sx < 0.4) // note: sx*sx >= 0
        	m_speedX = sx;
        if (sy * sy < 0.4) // note: sy*sy >= 0
        	m_speedY = sy;
        
        m_hitbox.move(m_speedX * elapse, m_speedY * elapse);
	}
}
