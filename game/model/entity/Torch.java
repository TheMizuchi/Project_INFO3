package model.entity;

import model.entity.behavior.TorchBehavior;
import view.MyCanvas;
import view.graphicEntity.TorchView;


public class Torch extends Entity {

	TorchView m_tv;
	public EntityInterface porteur;
	TorchBehavior m_tb;


	public Torch (double x, double y) {
		super(x, y, EntityProperties.TORCH);
		m_tv = new TorchView(this);
		m_ev = m_tv;
		m_tb = new TorchBehavior(this, m_tv);
		m_eb = m_tb;
		MyCanvas.getInstance().createEntityView(m_tv);
		m_tangible = false;
	}


	private static Torch INSTANCE = null;


	public static Torch getInstance (double x, double y) {

		if (INSTANCE == null) {
			INSTANCE = new Torch(x, y);
		}
		return INSTANCE;
	}

	public static Torch getInstance () {

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
