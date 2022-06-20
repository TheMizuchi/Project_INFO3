package model.entity;

import view.MyCanvas;
import view.graphicEntity.SkeletonView;


public class Doge extends Mob {

	DogeView m_sv;


	public Doge (double x, double y) {
		super(x, y, EntityProperties.DOGE);
		m_dv = new DogeView(this);
		m_ev = m_dv;
		MyCanvas.getInstance().createEntityView(m_dv);
	}

}
