package model.entity;

import view.MyCanvas;
import view.graphicEntity.DogeView;


public class MysteryMachine extends Mob {

	MMView m_mv;


	public MysteryMachine (double x, double y) {
		super(x, y, EntityProperties.DOGE);
		m_mv = new DogeView(this);
		m_ev = m_mv;
		MyCanvas.getInstance().createEntityView(m_mv);
	}

}
