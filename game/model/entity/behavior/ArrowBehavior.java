
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
		double ang = Math.PI / 8;
		double angDir = vecDir.getAngle();

		if (ang <= angDir && angDir <= 3 * ang) {
			m_av.hautdroite();
		} else if (3 * ang <= angDir && angDir <= 5 * ang) {
			m_av.haut();
		} else if (5 * ang <= angDir && angDir <= 7 * ang) {
			m_av.hautgauche();
		} else if (9 * ang <= angDir && angDir <= 11 * ang) {
			m_av.basgauche();
		} else if (11 * ang <= angDir && angDir <= 13 * ang) {
			m_av.bas();
		} else if (13 * ang <= angDir && angDir <= 15 * ang) {
			m_av.basdroite();
		}

		return dir.move(vecDir);
	}

}