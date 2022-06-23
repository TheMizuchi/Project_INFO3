package model.entity.behavior;

import model.entity.Entity;
import view.graphicEntity.SkeletonView;


public class SkeletonBehavior extends EntityBehavior {

	SkeletonView m_sv;


	public SkeletonBehavior (Entity e, SkeletonView ev) {
		super(e, ev);
		m_sv = ev;
	}
}
