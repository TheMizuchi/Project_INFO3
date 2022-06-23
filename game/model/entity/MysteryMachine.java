package model.entity;

import view.MyCanvas;
import view.graphicEntity.MysteryView;
import model.entity.behavior.CowboyBehavior;
import model.entity.behavior.MysteryBehavior;


public class MysteryMachine extends Mob {

	MysteryView m_mv;
	MysteryBehavior m_mb;

	public MysteryMachine (double x, double y) {
		super(x, y, EntityProperties.MYSTERY);
		m_mv = new MysteryView(this);
		m_ev = m_mv;
		m_mb = new MysteryBehavior(this, m_mv);
		this.eb = m_mb;
		MyCanvas.getInstance().createEntityView(m_mv);
		m_tangible = false;
	}

}
