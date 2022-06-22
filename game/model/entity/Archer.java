package model.entity;

import view.MyCanvas;
import view.graphicEntity.ArcherView;


public class Archer extends Mob {

	ArcherView m_av;


	public Archer (double x, double y) {
		super(x, y, EntityProperties.ARCHER);
		m_av = new ArcherView(this);
		m_ev = m_av;
		MyCanvas.getInstance().createEntityView(m_av);
	}
}
