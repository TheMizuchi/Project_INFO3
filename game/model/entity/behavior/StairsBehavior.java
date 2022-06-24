package model.entity.behavior;

import edu.polytech.oop.collections.IList;
import edu.polytech.oop.collections.LinkedList;
import model.entity.Entity;
import model.entity.EntityType;
import model.entity.J1;
import model.entity.J2;
import model.entity.Stairs;
import view.graphicEntity.StairsView;


public class StairsBehavior extends EntityBehavior {

	StairsView m_sv;


	public StairsBehavior (Entity e, StairsView ev) {
		super(e, ev);
		m_sv = ev;
	}

	//Changer d'étage
	public void pop () {

	}

	public boolean gotStuff () {

		int proximity = 1;

		if (this.e.distance(J1.getInstance()) > proximity && (this.e.distance(J2.getInstance()) > proximity))
			return false;

		LinkedList entities = ((Stairs) this.e).getRoom().getListeEntity();
		IList.Iterator iter = entities.iterator();

		while (iter.hasNext()) {
			Entity e = (Entity) iter.next();
			if (e.getType() == EntityType.ENEMY)
				return false;
		}

		return true;
	}
}
