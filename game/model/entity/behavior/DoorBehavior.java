package model.entity.behavior;

import model.entity.Door;
import model.entity.Entity;
import view.graphicEntity.DoorView;


public class DoorBehavior extends EntityBehavior {

	DoorView m_dv;


	public DoorBehavior (Entity e, DoorView ev) {
		super(e, ev);
		m_dv = ev;
	}

	//Ouvrir porte
	@Override
	public void pop () {
		Door d = (Door) this.e;
		d.nb_frame_open = 0;
		m_dv.opening();
		d.setTangible(false);
	}

	//Fermer porte
	@Override
	public void wizz () {
		Door d = (Door) this.e;
		d.setTangible(true);
		m_dv.closing();
	}

	@Override
	public boolean gotStuff () {
		return ((Door) e).shouldIOpenDoor();
	}

	@Override
	public void store () {
		Door d = (Door) this.e;

		if (d.nb_frame_open >= 7) {

			m_dv.opened();
		} else {
			d.nb_frame_open++;
		}
	}
}
