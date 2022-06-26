package model.entity;

import model.entity.behavior.KeyBehavior;
import view.MyCanvas;
import view.graphicEntity.KeyView;


public class Key extends Entity {

	static Key m_instance;
	KeyView m_kv;
	public EntityInterface porteur;
	KeyBehavior m_kb;


	public Key (double x, double y) {
		super(x, y, EntityProperties.KEY);
		m_kv = new KeyView(this);
		m_ev = m_kv;
		m_kb = new KeyBehavior(this, m_kv);
		m_eb = m_kb;
		MyCanvas.getInstance().createEntityView(m_kv);
		m_tangible = false;
		porteur = null;
	}

	public static Key getInstance (double x, double y) {
		if (m_instance == null)
			m_instance = new Key(x, y);
		return m_instance;
	}

	public static Key getInstance () {
		if (m_instance == null)
			throw new RuntimeException("Key not yet created");
		return m_instance;
	}

	public void updatePorteur (EntityInterface e) {
		porteur = e;
	}

	public void update (Entity e) {
		// si automate, faire un step
		m_hitbox.move(e);
	}

	public void hide () {
		m_kv.hide();
	}

	@Override
	void takeDamages (int damages) {
		return;
	}

	public void setHitBox (Hitbox hit) {
		m_hitbox = hit;
	}
}
