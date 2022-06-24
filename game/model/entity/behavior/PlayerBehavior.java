package model.entity.behavior;

import controller.RefAutomata;
import model.entity.Entity;
import model.entity.EntityProperties;
import model.entity.Key;
import model.entity.Mob;
import model.entity.Player;
import model.entity.Torch;
import model.entity.Vector;
import view.graphicEntity.EntityView;


public class PlayerBehavior extends EntityBehavior {

	EntityView ev;


	public PlayerBehavior (Entity e, EntityView ev) {
		super(e, ev);
		this.ev = ev;
	}

	@Override
	public void pick () {
		Torch torch = Torch.getInstance();
		Key key = Key.getInstance();

		if (this.e.distance(key) <= 2 && key.porteur == null) {
			key.porteur = this.e;
			key.hide();
		}

		else if (this.equals(torch.porteur)) {
			torch.porteur = null;
		} else if (this.e.distance(torch) <= 2) {
			torch.porteur = this.e;
		}
	}

	public void wizz (long possCD, EntityProperties ep, Vector v) {

		if (possCD == 0) {

			Mob closestTarget = (Mob) this.e.closest(true);

			if (closestTarget != null && this.e.distance(closestTarget) < Player.POSSESSION_RANGE) {
				closestTarget.devientGentil(ep, v.clone(), (Player) this.e);
				((Player) this.e).setAutomata(new RefAutomata(this.e, true));
				((Player) this.e).setPossessedMob(closestTarget);
				((Player) this.e).setTangible(false);
				((Player) this.e).hide();
				((Player) this.e).setCam(closestTarget);

				// On arrete l'animation de déplacement s'il y en a une
				if (v.getX() != 0 || v.getY() != 0) {
					this.ev.walk();
				}
			}
		}
	}

}
