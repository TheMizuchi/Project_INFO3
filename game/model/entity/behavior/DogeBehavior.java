package model.entity.behavior;

import model.entity.Doge;
import model.entity.Entity;
import view.graphicEntity.DogeView;


public class DogeBehavior extends EntityBehavior {

	DogeView m_dv;


	public DogeBehavior (Entity e, DogeView ev) {
		super(e, ev);
		m_dv = ev;
	}

	@Override
	public void pop () {
		((Doge) this.e).getAngry();
	}
}
