package model.entity;

import view.MyCanvas;
import view.graphicEntity.BloonView;


public class Bloon extends Mob {

	BloonView m_bv;
	int level;


	public Bloon (double x, double y) {
		super(x, y, EntityProperties.BLOON);
		m_bv = new BloonView(this, 2);
		m_ev = m_bv;
		MyCanvas.getInstance().createEntityView(m_bv);
	}

	public void setLevel (int n) {
		this.level = n;
		m_bv.setLevel(n);
	}

	@Override
	public void pop () {

		if (level > 0) {
			Bloon fils = (Bloon) Entity.createEntity(this.getPosX(), this.getPosY(), EntityProperties.BLOON);
			fils.setLevel(level - 1);
			fils = (Bloon) Entity.createEntity(this.getPosX(), this.getPosY(), EntityProperties.BLOON);
			fils.setLevel(level - 1);
		}
		m_bv.explode();
	}

}
