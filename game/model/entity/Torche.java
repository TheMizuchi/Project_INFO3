package model.entity;

import model.Model;
import view.MyCanvas;
import view.graphicEntity.TorcheView;

public class Torche extends Entity{

		TorcheView m_tv;
		EntityInterface porteur;
		
		
		public Torche (double x, double y) {
			super(x, y, Model.TORCHE_ID);
			m_tv = new TorcheView(this);
			m_ev = m_tv;
			MyCanvas.getInstance().createEntityView(m_tv);
		}
		
		public void updatePorteur (EntityInterface e) {
			porteur = e;
		}
		
		@Override
		public void update(long elapsed) {
			// si automate, faire un step
			m_hitbox.move(porteur);
		}
}
