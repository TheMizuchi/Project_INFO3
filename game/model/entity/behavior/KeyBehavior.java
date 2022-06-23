package model.entity.behavior;

import model.entity.Entity;
import view.graphicEntity.KeyView;


public class KeyBehavior extends EntityBehavior {

	KeyView m_kv;


	public KeyBehavior (Entity e, KeyView ev) {
		super(e, ev);
		m_kv = ev;
	}
}
