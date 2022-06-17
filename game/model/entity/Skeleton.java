package model.entity;

import model.Model;
import view.MyCanvas;
import view.graphicEntity.BloonView;


public class Skeleton extends Entity {

//	ZombieView m_zv;


	public Skeleton (double x, double y) {
		super(x, y, Model.SKELETON_ID);
//		m_bv = new ZombieView(this);
//		m_ev = m_bv;
//		MyCanvas.getInstance().createEntityView(m_bv);
	}
}
