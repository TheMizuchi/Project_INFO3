package model.entity;

import model.entity.behavior.BloonBehavior;
import view.MyCanvas;
import view.graphicEntity.BloonView;


public class Bloon extends Mob {

	BloonView m_bv;
	int level;
	BloonBehavior m_bb;


	public Bloon (double x, double y) {
		super(x, y, EntityProperties.BLOON);
		m_bv = new BloonView(this, 2);
		this.setLevel(2);
		m_ev = m_bv;
		m_bb = new BloonBehavior(this, m_bv);
		m_mb = m_bb;
		m_eb = m_bb;
		MyCanvas.getInstance().createEntityView(m_bv);
		m_tangible = false;
		cdDmgTaken = 100;
	}

	public int getLevel () {
		return this.level;
	}

	public void setLevel (int n) {
		this.level = n;
		m_bv.setLevel(n);
	}
}