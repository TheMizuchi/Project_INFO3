package model.entity;

import model.LightSource;
import model.Model;
import model.entity.behavior.TorchBehavior;
import view.MyCanvas;
import view.graphicEntity.TorchView;


public class Torch extends Entity {

	private static final double HOLDED_RADIUS = 5;
	private static final double GROUND_RADIUS = 6.5;

	TorchView m_tv;
	EntityInterface porteur;
	TorchBehavior m_tb;
	LightSource m_ls;


	private Torch (double x, double y) {
		super(x, y, EntityProperties.TORCH);
		m_tv = new TorchView(this);
		m_ev = m_tv;
		m_tb = new TorchBehavior(this, m_tv);
		m_eb = m_tb;
		m_ls = Model.getInstance().createLightSource(this);
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

	public void update () {
		// si automate, faire un step

		if (this.porteur != null) {
			m_ls.setRadius(HOLDED_RADIUS);
			m_hitbox.move((Entity) this.porteur);
		} else {
			m_ls.setRadius(GROUND_RADIUS);
		}
	}

	@Override
	void takeDamages (int damages) {
		return;
	}
}
