package model.entity;

import model.Model;
import view.MyCanvas;
import view.graphicEntity.TorchView;


public class Torch extends Entity {

	TorchView m_tv;
	EntityInterface porteur;


	public Torch (double x, double y) {
		super(x, y, Model.TORCH_ID);
		m_tv = new TorchView(this);
		m_ev = m_tv;
		MyCanvas.getInstance().createEntityView(m_tv);
	}

	public void updatePorteur (EntityInterface e) {
		porteur = e;
	}

	@Override
	public void update (long elapsed) {
		// si automate, faire un step
		m_hitbox.move(porteur);
	}
}
