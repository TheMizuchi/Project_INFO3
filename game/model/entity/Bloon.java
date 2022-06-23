package model.entity;

import model.Model;
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
		m_tangible = false;
	}

	public void setLevel (int n) {
		this.level = n;
		m_bv.setLevel(n);
	}

	@Override
	public void egg (double orientationx, double orientationy) {
		Model m;
		m = Model.getInstance();

		if (level >= 0) {
			Entity e = m.createEntity(getPosX(), getPosY(), this.m_entityProperties);
			((Bloon) e).setLevel(level - 1);
			m.createLightSource(e);
			e = m.createEntity(getPosX(), getPosY(), this.m_entityProperties);
			((Bloon) e).setLevel(level - 1);
			m.createLightSource(e);
		}
	}

	@Override
	public void pop () {
		m_bv.explode();
		//Die now
	}

}
