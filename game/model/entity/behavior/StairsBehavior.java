package model.entity.behavior;

import edu.polytech.oop.collections.IList;
import model.Model;
import model.entity.Entity;
import model.entity.EntityType;
import model.entity.J1;
import model.entity.J2;
import view.graphicEntity.StairsView;


public class StairsBehavior extends EntityBehavior {

	public StairsBehavior (Entity e, StairsView ev) {
		super(e, ev);
	}

	@Override
	public boolean gotStuff () {

		double proximity = 0.5;

		if (this.e.distance(J1.getInstance()) > proximity && this.e.distance(J2.getInstance()) > proximity)
			return false;

		return true;
	}

	@Override
	public boolean gotPower () {
		IList entities = Model.getlistEntity();
		IList.Iterator iter = entities.iterator();

		while (iter.hasNext()) {
			Entity e = (Entity) iter.next();
			if (e.getType() == EntityType.ENEMY)
				return false;
		}
		return true;
	}

	//Changer d'étage
	@Override
	public void wizz () {
		Model.getInstance().newLevel();
		System.out.println("Next level");
	}

	//Apparition de la porte
	@Override
	public void pop () {}
}
