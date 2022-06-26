
package model.entity.behavior;

import model.entity.Direction;
import model.entity.Entity;
import model.entity.Vector;
import view.graphicEntity.ArrowView;


public class ArrowBehavior extends MobBehavior {

	ArrowView m_av;


	public ArrowBehavior (Entity e, ArrowView av) {
		super(e, av);
		m_av = av;
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
		double ang = Math.PI / 4;
		boolean b = dir.move(vecDir);
		double angDir = vecDir.getAngle();

		if (angDir == ang) {
			m_av.hautdroite();
		} else if (angDir == 2 * ang) {
			m_av.haut();
		} else if (angDir == 3 * ang) {
			m_av.hautgauche();
		} else if (angDir == 5 * ang) {
			m_av.basgauche();
		} else if (angDir == 6 * ang) {
			m_av.bas();
		} else if (angDir == 7 * ang) {
			m_av.basdroite();
		}

		return b;
	}

}