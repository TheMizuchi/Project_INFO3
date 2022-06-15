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
	private CowboyView cv;

	// Liste d'items

	//


	public Entity (double x, double y, int ID) {
		m_ID = ID;
		m_orientation = new Direction();
		m_hitbox = new Hitbox(x, y, 0.5, 0.5);
		m_automata = new RefAutomata(this);
		this.cv = new CowboyView(this);
		MyCanvas.getInstance().createEntityView(this.cv);
	}

	public boolean getOrientation () {
		return m_orientation.getDirection() % 2 == 1;

	}

	public double getPosX () {
		return m_hitbox.getX();
	}

	public double getPosY () {
		return m_hitbox.getY();
	}

	public void move (double dx, double dy) {
		m_hitbox.move(dx, dy);
		m_orientation.update_orentaiton(dx, dy);
	}

	public void update () {
		m_automata.step();
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
		this.cv.spin();

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
	public void move (Direction orentation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rotation (Direction orientation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void hit (Direction orentation) {
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
