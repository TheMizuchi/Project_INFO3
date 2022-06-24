package model.entity.behavior;

import model.entity.Entity;
import view.graphicEntity.DoorView;


public class DoorBehavior extends EntityBehavior {

	DoorView m_dv;


	public DoorBehavior (Entity e, DoorView ev) {
		super(e, ev);
		m_dv = ev;
	}

	//Ouvrir porte
	public void pop (int nb_frame_open, boolean tangible) {
		nb_frame_open = 0;
		m_dv.opening();
		tangible = false;
	}

	//Fermer porte
	public void wizz (boolean tangible) {
		tangible = true;
		m_dv.closing();
	}

	public void store (int nb_frame_open) {

		if (nb_frame_open >= 7) {

			m_dv.opened();
		} else {
			nb_frame_open++;
		}
	}
}
