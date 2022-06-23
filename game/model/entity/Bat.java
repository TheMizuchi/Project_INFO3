package model.entity;

import model.entity.behavior.BatBehavior;
import view.MyCanvas;
import view.graphicEntity.BatView;


public class Bat extends Mob {

	BatView m_bv;
	BatBehavior m_bb;
	

	public Bat (double x, double y) {
		super(x, y, EntityProperties.BAT);
		m_bv = new BatView(this);
		m_ev = m_bv;
		m_bb = new BatBehavior(this, m_bv);
		this.eb = m_bb;
		MyCanvas.getInstance().createEntityView(m_bv);
	}

}
