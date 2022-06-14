package controller;

import model.entity.Entity;

public abstract class IAction {

	protected int m_percent;


	public abstract boolean apply (Entity e);

}
