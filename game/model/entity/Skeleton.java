package model.entity;

import model.entity.behavior.SkeletonBehavior;
import view.MyCanvas;
import view.graphicEntity.SkeletonView;


public class Skeleton extends Mob {

	SkeletonView m_sv;
	SkeletonBehavior m_sb;

	public Skeleton (double x, double y) {
		super(x, y, EntityProperties.SKELETON);
		m_sv = new SkeletonView(this);
		m_ev = m_sv;
		m_sb = new SkeletonBehavior(this, m_sv);
		this.eb = m_sb;
		MyCanvas.getInstance().createEntityView(m_sv);
	}

}
