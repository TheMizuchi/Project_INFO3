package model.entity.behavior;

import model.entity.Entity;
import model.entity.Mob;
import model.entity.Player;
import model.entity.Vector;
import view.graphicEntity.EntityView;


public abstract class PlayerBehavior extends EntityBehavior {

	protected static final long POSSESSION_CD = 30;

	final static double SLOW_TORCHE = 0.20;
	final static double POSSESSION_RANGE = 10;
	long m_possessionCD;
	Mob m_possessing;


	public PlayerBehavior (Entity e, EntityView ev) {
		super(e, ev);
	}

	abstract public void update (long elapsed);

	@Override
	public void pick () {
		((Player) this.e).pickTorch();
	}

	@Override
	public void wizz () {
		((Player) this.e).possession();
	}

	@Override
	public void pop () {
		((Player) this.e).pick();
	}

	@Override
	public void hit (Vector v) {
		System.out.println("meow c'est trop bien els attaques");
		super.hit(v);
		ev.attack();
	}
}
