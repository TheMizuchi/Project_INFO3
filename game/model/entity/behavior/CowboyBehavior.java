package model.entity.behavior;

import model.entity.Entity;
import view.graphicEntity.CowboyView;


public class CowboyBehavior extends EntityBehavior {

	CowboyView m_cv;


	public CowboyBehavior (Entity e, CowboyView ev) {
		super(e);
		m_cv = ev;
	}
}
