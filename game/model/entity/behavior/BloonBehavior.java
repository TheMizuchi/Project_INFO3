package model.entity.behavior;

import model.Model;
import model.entity.Bloon;
import model.entity.Entity;
import model.entity.EntityProperties;
import model.entity.Hitbox;
import model.entity.Point;
import view.graphicEntity.BloonView;


public class BloonBehavior extends MobBehavior {

	public BloonBehavior (Entity e, BloonView ev) {
		super(e, ev);
	}

	@Override
	public void egg (double orientationx, double orientationy, Hitbox hitbox, EntityProperties entityProperties) {
		Model m;
		m = Model.getInstance();

		if (((Bloon) this.e).getLevel() > 0) {
			int lvl = ((Bloon) this.e).getLevel() - 1;
			//création premier Bloon
			Bloon e = (Bloon) m.createEntity(this.e.getPosX(), this.e.getPosY(), entityProperties);
			((Bloon) e).setLevel(lvl);
			//création second Bloon
			//TODO : utiliser dans un if la condition topAvailable()
			if (spawnAvailable(0.3, 0.3))
				e = (Bloon) m.createEntity(this.e.getPosX() + 0.5, this.e.getPosY() + 0.5, entityProperties);
			else if (spawnAvailable(-0.3, -0.3))
				e = (Bloon) m.createEntity(this.e.getPosX() + 0.5, this.e.getPosY() + 0.5, entityProperties);
			else if (spawnAvailable(-0.3, 0.3))
				e = (Bloon) m.createEntity(this.e.getPosX() + 0.5, this.e.getPosY() + 0.5, entityProperties);
			else if (spawnAvailable(0.3, -0.3))
				e = (Bloon) m.createEntity(this.e.getPosX() + 0.5, this.e.getPosY() + 0.5, entityProperties);
			((Bloon) e).setLevel(lvl);
		}
	}

	@Override
	public void pop () {
		// TODO Bloon Pop
	}

	private boolean spawnAvailable (double dx, double dy) {
		Point p1 = new Point(e.m_hitbox.getP1().getX() + dx, e.m_hitbox.getP1().getY() + dy);
		Point p2 = new Point(e.m_hitbox.getP2().getX() + dx, e.m_hitbox.getP2().getY() + dy);
		Point p3 = new Point(e.m_hitbox.getP3().getX() + dx, e.m_hitbox.getP3().getY() + dy);
		Point p4 = new Point(e.m_hitbox.getP4().getX() + dx, e.m_hitbox.getP4().getY() + dy);

		if (e.getHitbox().deplacementValide(p1, p2, p3, p4))
			return true;

		return false;
	}

}
