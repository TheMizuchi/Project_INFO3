package model.entity.behavior;

import model.entity.Entity;
import model.entity.Player;
import view.graphicEntity.EntityView;


public class PlayerNormalGroundBehavior extends PlayerBehavior {

	public PlayerNormalGroundBehavior (Entity e, EntityView ev) {
		super(e, ev);
	}

	@Override
	public void update (long elapsed) {
		((Player) this.e).updateOnNormalGround(elapsed);
	}

}
