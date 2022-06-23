package model.entity;

import view.MyCanvas;
import view.graphicEntity.ArcherView;
import model.entity.behavior.ArcherBehavior;

public class Archer extends Mob {

	ArcherView m_av;
	ArcherBehavior m_ab;


	public Archer (double x, double y) {
		super(x, y, EntityProperties.ARCHER);
		m_av = new ArcherView(this);
		m_ev = m_av;
		m_ab = new ArcherBehavior(this, m_av);
		this.eb = m_ab;
		MyCanvas.getInstance().createEntityView(m_av);
	}
}
