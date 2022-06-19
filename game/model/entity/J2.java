package model.entity;

import model.Model;
import view.MyCanvas;
import view.graphicEntity.J2View;


public class J2 extends Entity {

	J2View m_jv;
	Torch m_torch;


	public J2 (double x, double y) {
		super(x, y, Model.J2_ID);
		m_jv = new J2View(this);
		m_ev = m_jv;
		MyCanvas.getInstance().createEntityView(m_jv);
	}

	@Override
	public void update (long elapsed) {
		// déplacement
		m_automata.step();
		double speedX = super.m_vecDir.getX() * ENTITY_MAX_SPEED;
		double speedY = super.m_vecDir.getY() * ENTITY_MAX_SPEED;
		m_hitbox.move(speedX * elapsed / 1000, speedY * elapsed / 1000);
		if (m_torch != null)
			m_torch.update(this);
	}

	@Override
	public void pick () {

		if (m_torch != null) {
			m_torch = null;
			return;
		}
		Torch torche = (Torch) closest(new TypeEntity(Model.TORCH_ID));
		if (distance(torche) <= 2)
			m_torch = torche;
		return;
	}
}
