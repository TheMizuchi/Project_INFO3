package model.entity;

public abstract class Player extends Entity {

	public Player (double x, double y, int ID) {
		super(x, y, ID);
	}

	@Override
	public void update (long elapsed) {
		Torch torch = Torch.getInstance();
		// déplacement
		m_automata.step();
		double speedX = super.m_vecDir.getX() * ENTITY_MAX_SPEED;
		double speedY = super.m_vecDir.getY() * ENTITY_MAX_SPEED;
		m_hitbox.move(speedX * elapsed / 1000, speedY * elapsed / 1000);
		if (this.equals(torch.porteur))
			torch.update(this);
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
