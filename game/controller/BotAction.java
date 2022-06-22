package controller;

import model.entity.Entity;


public abstract class BotAction {

	protected double m_percent;


	public BotAction () {
		m_percent = -1;
	}

	public void setPercent (double percent) {
		m_percent = percent;
	}

	public double getPercent () {
		return m_percent;
	}

	public abstract boolean apply (Entity e, RefAutomata aut);

}
