
package model.entity.behavior;

import model.entity.Direction;
import model.entity.Entity;
import model.entity.Vector;
import view.graphicEntity.ArrowView;


public class ArrowBehavior extends MobBehavior {

	public ArrowBehavior (Entity e, ArrowView av) {
		super(e, av);
	}

	@Override
	public void pop () {
		// TODO Arrow Pop
	}

	@Override
	public void wizz () {
		// TODO Arrow Wizz
	}

	@Override
	public boolean move (Direction dir, Vector vecDir) {
		return dir.move(vecDir);
	}

}