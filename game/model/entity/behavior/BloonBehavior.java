package model.entity.behavior;

import model.Model;
import model.entity.Bloon;
import model.entity.Entity;
import model.entity.EntityProperties;
import model.entity.Hitbox;
import view.graphicEntity.BloonView;


public class BloonBehavior extends MobBehavior {

	BloonView m_bv;


	public BloonBehavior (Entity e, BloonView ev) {
		super(e, ev);
		m_bv = ev;

	}

	@Override
	public void egg (double orientationx, double orientationy, Hitbox hitbox, EntityProperties entityProperties) {
		Model m;
		m = Model.getInstance();

		if (((Bloon) this.e).getLevel() > 0) {
			int lvl = ((Bloon) this.e).getLevel() - 1;
			System.out.println(((Bloon) this.e).getLevel());
			Bloon e = (Bloon) m.createEntity(this.e.getPosX(), this.e.getPosY(), entityProperties);
			((Bloon) e).setLevel(lvl);
			m.createLightSource(e);
			System.out.println("J'ai le niveau :" + e.getLevel());
			e = (Bloon) m.createEntity(this.e.getPosX(), this.e.getPosY(), entityProperties);
			((Bloon) e).setLevel(lvl);
			m.createLightSource(e);
			System.out.println("Et moi j'ai le niveau :" + e.getLevel());
		}
	}

	@Override
	public void pop () {
		// TODO Bloon Pop
	}

}
