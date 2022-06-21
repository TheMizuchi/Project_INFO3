package model.entity;

import view.MyCanvas;
import view.graphicEntity.TorchView;


public class Door extends Entity {

	TorchView m_tv;
	EntityInterface porteur;


	public Door (double x, double y) {
		super(x, y, EntityProperties.TORCH);
		m_tv = new TorchView(this);
		m_ev = m_tv;
		MyCanvas.getInstance().createEntityView(m_tv);
		m_tangible = false;
	}


	private static Door INSTANCE = null;


	public static Door getInstance (double x, double y) {

		if (INSTANCE == null) {
			INSTANCE = new Door(x, y);
		}
		return INSTANCE;
	}

	public static Door getInstance () {

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
