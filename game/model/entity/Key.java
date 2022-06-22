package model.entity;

import view.MyCanvas;


public class Key extends Entity {

	KeyView m_tv;
	EntityInterface porteur;


	public Key (double x, double y) {
		super(x, y, EntityProperties.TORCH);
		m_tv = new KeyView(this);
		m_ev = m_tv;
		MyCanvas.getInstance().createEntityView(m_tv);
		m_tangible = false;
	}


	private static Key INSTANCE = null;


	public static Key getInstance (double x, double y) {

		if (INSTANCE == null) {
			INSTANCE = new Key(x, y);
		}
		return INSTANCE;
	}

	public static Key getInstance () {

		if (INSTANCE == null) {
			throw new RuntimeException("Torch isn't instance yet what are you doing bro ?");
		}
		return INSTANCE;
	}

	public void updatePorteur (EntityInterface e) {
		porteur = e;
	}

	public void update (Entity e) {
		// si automate, faire un step
		m_hitbox.move(e);
	}

}
