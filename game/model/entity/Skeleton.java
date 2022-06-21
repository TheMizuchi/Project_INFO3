package model.entity;

import view.MyCanvas;
import view.graphicEntity.SkeletonView;


public class Skeleton extends Mob {

	SkeletonView m_sv;


	public Skeleton (double x, double y) {
		super(x, y, EntityProperties.SKELETON);
		m_sv = new SkeletonView(this);
		m_ev = m_sv;
		MyCanvas.getInstance().createEntityView(m_sv);
	}

	public Skeleton (double x, double y, Object object) {
		super(x, y, EntityProperties.SKELETON);
	}

}