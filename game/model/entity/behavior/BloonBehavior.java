package model.entity.behavior;

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

		if (((Bloon) this.e).getLevel() >= 0) {
			Bloon e = (Bloon) m.createEntity(this.e.getPosX(), this.e.getPosY(), entityProperties);
			((Bloon) e).setLevel(e.getLevel() - 1);
			m.createLightSource(e);
			e = (Bloon) m.createEntity(this.e.getPosX(), this.e.getPosY(), entityProperties);
			((Bloon) e).setLevel(e.getLevel() - 1);
			m.createLightSource(e);
		}
	}

	@Override
	public void pop () {
		// TODO Bloon Pop
	}

}
