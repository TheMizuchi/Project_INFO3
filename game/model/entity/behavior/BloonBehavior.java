package model.entity.behavior;

import model.entity.Entity;
import view.graphicEntity.BloonView;


public class BloonBehavior extends EntityBehavior {

	BloonView m_bv;


	public BloonBehavior (Entity e, BloonView ev) {
		super(e, ev);
		m_bv = ev;

	}

}
