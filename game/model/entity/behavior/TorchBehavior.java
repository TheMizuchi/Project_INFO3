package model.entity.behavior;

import model.entity.Entity;
import view.graphicEntity.TorchView;


public class TorchBehavior extends EntityBehavior {

	TorchView m_tv;


	public TorchBehavior (Entity e, TorchView ev) {
		super(e, ev);
		m_tv = ev;
	}

	@Override
	public void pop () {
		// TODO Torch Pop
	}

	@Override
	public void wizz () {
		// TODO Torch Wizz
	}
}
