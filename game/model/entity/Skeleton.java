package model.entity;

import model.Model;
import view.MyCanvas;
import view.graphicEntity.BloonView;
import view.graphicEntity.SkeletonView;


public class Skeleton extends Entity {

	SkeletonView m_sv;


	public Skeleton (double x, double y) {
		super(x, y, Model.SKELETON_ID);
		m_sv = new SkeletonView(this);
		m_ev = m_sv;
		MyCanvas.getInstance().createEntityView(m_sv);
	}
}
