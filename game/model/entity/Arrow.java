package model.entity;

import model.entity.behavior.ArrowBehavior;
import model.map.TileType;
import view.MyCanvas;
import view.graphicEntity.ArrowView;


public class Arrow extends Mob {

	@Override
	public double getSpeed () {
		return 5;
	}


	ArrowView m_av;
	ArrowBehavior m_ab;


	public Arrow (double x, double y) {
		super(x, y, EntityProperties.ARROW);
		m_av = new ArrowView(this);
		m_ev = m_av;
		m_ab = new ArrowBehavior(this, m_av);
		m_eb = m_ab;
		MyCanvas.getInstance().createEntityView(m_av);
		m_blockInterdit.remove(TileType.VOID);
		m_tangible = false;
		m_hurtbox.setTo(m_hitbox);
	}

	public void setVector (Vector v) {
		m_vecDir = v;
	}

	@Override
	void takeDamages (int damages) {
		return;
	}

	public void setNbDamages (int dmg) {
		m_nbDamages = dmg;
	}

	public void setHitBox (Hitbox hit) {
		m_hitbox = hit;
	}

	void attack (Entity cible) {

		if (cible.getClass() == Arrow.class)
			return;

		if (!isDeath()) {
			cible.takeDamages(m_nbDamages);
		}
		this.deleteEntity();
	}

	//	@Override
	//	public void move (Direction orientation) {
	//
	//		m_eb.move(orientation, m_vecDir);
	//	}
}
