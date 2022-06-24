package model.entity;

import model.entity.behavior.MysteryBehavior;
import view.MyCanvas;
import view.graphicEntity.MysteryView;


public class MysteryMachine extends Mob {

	MysteryView m_mmv;
	MysteryBehavior m_mmb;


	public MysteryMachine (double x, double y) {
		super(x, y, EntityProperties.MYSTERY);
		m_mmv = new MysteryView(this);
		m_ev = m_mmv;
		m_mb = new MysteryBehavior(this, m_mmv);
		m_mb = m_mmb;
		m_eb = m_mmb;
		MyCanvas.getInstance().createEntityView(m_mmv);
		m_tangible = false;
	}

	public MysteryMachine (double x, double y, Object object) {
		super(x, y, EntityProperties.MYSTERY);
	}

}