package model.entity;

import model.Model;
import view.MyCanvas;

public class Bat extends Entity{
	//BatView m_zv;


	public Bat (double x, double y) {
		super(x, y, Model.ZOMBIE_ID);
		//m_bv = new ZombieView(this);
		//m_ev = m_bv;
		//MyCanvas.getInstance().createEntityView(m_bv);
	}
}
