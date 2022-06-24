package model.entity.behavior;

import model.entity.Entity;
import view.graphicEntity.ArcherView;


public class ArcherBehavior extends EntityBehavior {

	ArcherView m_av;


	public ArcherBehavior (Entity e, ArcherView ev) {
		super(e, ev);
		m_av = ev;
	}
}