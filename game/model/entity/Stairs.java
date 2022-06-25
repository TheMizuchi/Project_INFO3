package model.entity;

import model.Model;
import model.entity.behavior.StairsBehavior;
import model.map.generator.Room;
import view.MyCanvas;
import view.graphicEntity.StairsView;


public class Stairs extends Entity {

	Room m_room;
	StairsView m_sv;
	StairsBehavior m_sb;


	public Stairs (double x, double y) {
		super(x, y, EntityProperties.STAIRS);
		m_tangible = false;
		m_room = Model.getMap().getBoss();
		m_sv = new StairsView(this);
		m_ev = m_sv;
		MyCanvas.getInstance().createEntityView(m_sv);
		m_sb = new StairsBehavior(this, m_sv);
		m_eb = m_sb;
	}

	public boolean shouldIEnterStairs () {
		return m_sb.gotStuff();
	}
	
	public boolean shouldIUnlock() {
		return m_sb.gotPower();
	}

	@Override
	void takeDamages (int damages) {
		return;
	}

}
