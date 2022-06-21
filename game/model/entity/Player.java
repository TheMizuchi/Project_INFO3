package model.entity;

import model.Camera;

public abstract class Player extends Entity {

	public Player (double x, double y, EntityProperties ep) {
		super(x, y, ep);
	}

	@Override
	public void update (long elapsed) {
		Torch torch = Torch.getInstance();
		// déplacement
		m_automata.step();
		double speedX = super.m_vecDir.getX() * ENTITY_MAX_SPEED;
		double speedY = super.m_vecDir.getY() * ENTITY_MAX_SPEED;
		
		if (Camera.getBlock()) {
			Entity autreJ = autreJ();
			double m_angle = m_vecDir.getAngle();
			if (m_angle < Math.PI && autreJ.m_hitbox.getY() < m_hitbox.getY())
				return;
			if (m_angle > Math.PI && autreJ.m_hitbox.getY() < m_hitbox.getY())
				return;
			if (m_angle > Math.PI/2 && m_angle < 3*Math.PI/2  && autreJ.m_hitbox.getX() > m_hitbox.getX())
				return;
			if ((m_angle < Math.PI/2 || m_angle > 3*Math.PI/2) && autreJ.m_hitbox.getX() < m_hitbox.getX())
				return;
		}
		
		m_hitbox.move(speedX * elapsed / 1000, speedY * elapsed / 1000);
		if (this.equals(torch.porteur))
			torch.update(this);
	}

	private Entity autreJ () {
		if (this == J1.getInstance())
			return J2.getInstance();
		return J1.getInstance();
	}

	@Override
	public void pick () {
		Torch torch = Torch.getInstance();

		if (this.equals(torch.porteur)) {
			torch.porteur = null;
		} else if (distance(torch) <= 2) {
			torch.porteur = this;
		}
	}
}
