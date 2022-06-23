package model.entity;

import model.entity.behavior.DogeBehavior;
import view.MyCanvas;
import view.graphicEntity.DogeView;


public class Doge extends Mob {

	DogeView m_dv;
	DogeBehavior m_db;

	public Doge (double x, double y) {
		super(x, y, EntityProperties.DOGE);
		m_dv = new DogeView(this);
		m_ev = m_dv;
		m_db = new DogeBehavior(this, m_dv);
		this.eb = m_db;
		MyCanvas.getInstance().createEntityView(m_dv);
	}

}
