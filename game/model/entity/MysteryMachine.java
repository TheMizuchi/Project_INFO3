package model.entity;

import view.MyCanvas;
import view.graphicEntity.MysteryView;


public class MysteryMachine extends Mob {

	MysteryView m_mv;


	public MysteryMachine (double x, double y) {
		super(x, y, EntityProperties.MYSTERY);
		m_mv = new MysteryView(this);
		m_ev = m_mv;
		MyCanvas.getInstance().createEntityView(m_mv);
	}

}
