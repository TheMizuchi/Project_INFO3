package model.entity.behavior;

import model.entity.Entity;
import view.graphicEntity.DogeView;


public class AngryDogeBehavior extends DogeBehavior {

	DogeView m_dv;


	public AngryDogeBehavior (Entity e, DogeView ev) {
		super(e, ev);
		m_dv = ev;
	}
}
