package model.entity.behavior;

import model.entity.Entity;
import view.graphicEntity.StairsView;


public class StairsBehavior extends EntityBehavior {

	StairsView m_sv;


	public StairsBehavior (Entity e, StairsView ev) {
		super(e, ev);
		m_sv = ev;
	}

	//Changer d'étage
	@Override
	public void pop () {

	}
}
