package model.entity;

import view.MyCanvas;
import view.graphicEntity.BloonView;


public class Bloon extends Mob {

	BloonView m_bv;


	public Bloon (double x, double y) {
		super(x, y, EntityProperties.BLOON);
		m_bv = new BloonView(this);
		m_ev = m_bv;
		MyCanvas.getInstance().createEntityView(m_bv);
	}

	//Constructeur pour créer entité sans view
	public Bloon (double x, double y, Object o) {
		super(x, y, EntityProperties.BLOON);
	}

	@Override
	public void pop () {
		m_bv.explode();
	}

}