package model.entity;

import model.entity.behavior.MysteryBehavior;
import view.MyCanvas;
import view.graphicEntity.MysteryView;


public class MysteryMachine extends Mob {

	MysteryView m_mv;
	MysteryBehavior m_mb;


	public MysteryMachine (double x, double y) {
		super(x, y, EntityProperties.MYSTERY);
		m_mv = new MysteryView(this);
		m_ev = m_mv;
		m_mb = new MysteryBehavior(this, m_mv);
		m_eb = m_mb;
		MyCanvas.getInstance().createEntityView(m_mv);
		m_tangible = false;
	}

}