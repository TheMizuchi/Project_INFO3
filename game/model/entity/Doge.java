package model.entity;

import model.entity.behavior.AngryDogeBehavior;
import model.entity.behavior.DogeBehavior;
import view.MyCanvas;
import view.graphicEntity.DogeView;


public class Doge extends Mob {

	DogeView m_dv;
	DogeBehavior m_db;


	public Doge (double x, double y) {
		super(x, y, EntityProperties.DOGE);
		m_pv = 3;
		m_dv = new DogeView(this);
		m_ev = m_dv;
		m_db = new DogeBehavior(this, m_dv);
		m_mb = m_db;
		m_eb = m_db;
		MyCanvas.getInstance().createEntityView(m_dv);
	}

	public void getAngry () {
		m_dv.getAngry();
		m_pv = 5;
		m_db = new AngryDogeBehavior(this, m_dv);
		m_eb = m_db;
		m_mb = m_db;
	}

}