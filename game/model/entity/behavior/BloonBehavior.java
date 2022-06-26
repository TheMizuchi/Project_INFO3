package model.entity.behavior;

import java.util.Random;

import model.Model;
import model.entity.Bloon;
import model.entity.Entity;
import model.entity.EntityProperties;
import model.entity.Hitbox;
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

			for (int i = 0; i < 2; i++) {
				((Bloon) m.createEntity(this.e.m_hitbox.getP1().getX(), this.e.m_hitbox.getP1().getY(), entityProperties)).setLevel(lvl);
			}
		}
	}

	@Override
	public void pop () {
		Random ran = new Random();
		e.waitt(ran.nextLong() % 1000);
	}

}
