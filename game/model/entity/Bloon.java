package model.entity;

import model.Model;
import view.MyCanvas;
import view.graphicEntity.BloonView;


public class Bloon extends Entity {

	BloonView m_bv;


	public Bloon (double x, double y) {
		super(x, y, Model.BLOON_ID);
		m_bv = new BloonView(this);
		m_ev = m_bv;
		MyCanvas.getInstance().createEntityView(m_bv);
	}
}
