package model.entity;

import controller.RefAutomata;
import edu.polytech.oop.collections.ICollection;
import model.Model;
import view.MyCanvas;
import view.graphicEntity.CowboyView;


public class Entity implements EntityInterface {

	public int m_ID;
	private Hitbox m_hitbox;
	Direction m_orientation;
	TypeEntity type;
	protected RefAutomata m_automata;
	private CowboyView m_cv;
	private double m_speedX;
	private double m_speedY;

	private static final double ENTITY_MAX_SPEED = 2; // vitesse par seconde
	private static final double ENTITY_MAX_SPEED_DIAGONAL = Math.sqrt(ENTITY_MAX_SPEED) / 2;

	// Liste d'items

	//


	public Entity (double x, double y, int ID) {
		m_ID = ID;
		m_orientation = new Direction();
		m_hitbox = new Hitbox(x, y, 0.5, 0.5);
		m_automata = new RefAutomata(this);

		this.m_cv = new CowboyView(this);
		MyCanvas.getInstance().createEntityView(this.m_cv);
	}

	public boolean getOrientation () {
		// T si gauche / north
		return m_orientation.getDirectionX() < 0 || m_orientation.getDirectionY() < 0;
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
		this.deplacement(elapsed);
	}

	void attack () {}

	void interact () {}

	@Override
	public boolean myDir (Direction orientation) {
		return m_orientation.getAngle() == orientation.getAngle();
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

				if (distMin > dist) {
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
	public void move (Direction orientation) {
		m_orientation.updateDirection(orientation);
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

	private void deplacement (long elapse) {
		double angle = m_orientation.toAngle();
		m_speedX = Math.cos(angle) * Math.abs(m_orientation.getDirectionX()) * ENTITY_MAX_SPEED;
		m_speedY = Math.sin(angle) * Math.abs(m_orientation.getDirectionY()) * ENTITY_MAX_SPEED;
		System.out.println(m_orientation.getDirectionX() + " y : " + m_orientation.getDirectionY() + " elapse : " + elapse);
		m_hitbox.move(m_speedX * elapse / 1000, m_speedY * elapse / 1000);
	}

}
