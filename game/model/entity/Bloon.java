package model.entity;

import model.entity.behavior.BloonBehavior;
import model.map.TileType;
import view.MyCanvas;
import view.graphicEntity.BloonView;


public class Bloon extends Mob {

	BloonView m_bv;
	int level;
	BloonBehavior m_bb;


	public Bloon (double x, double y, int level) {
		super(x, y, EntityProperties.BLOON);
		m_bv = new BloonView(this, level);
		this.setLevel(level);
		m_ev = m_bv;
		m_bb = new BloonBehavior(this, m_bv);
		m_mb = m_bb;
		m_eb = m_bb;
		MyCanvas.getInstance().createEntityView(m_bv);
		m_tangible = false;
		m_cdDmgTaken = 200;
		m_blockInterdit.remove(TileType.VOID);
	}

	public int getLevel () {
		return this.level;
	}

	public void setLevel (int n) {
		this.level = n;
		m_bv.setLevel(n);
	}

	void attack (Entity cible) {

		if (!isDeath() && cible.m_entityProperties != EntityProperties.BLOON) {
			cible.takeDamages(m_nbDamages);
		}
	}

}