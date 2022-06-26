package model.entity;

import model.entity.behavior.ArcherBehavior;
import view.MyCanvas;
import view.graphicEntity.ArcherView;


public class Archer extends Mob {

	@Override
	public double getDetectionRange () {
		return 3.5;
	}


	ArcherView m_av;
	ArcherBehavior m_ab;


	public Archer (double x, double y) {
		super(x, y, EntityProperties.ARCHER);
		m_av = new ArcherView(this);
		m_ev = m_av;
		m_ab = new ArcherBehavior(this, m_av);
		m_mb = m_ab;
		m_eb = m_ab;
		MyCanvas.getInstance().createEntityView(m_av);
	}

}
