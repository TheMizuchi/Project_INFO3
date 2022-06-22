package controller.condition.tree;

import controller.ICondition;
import model.entity.Entity;


public class BotFunCall implements ICondition {

	public String m_name;
	public Object m_p1;
	public String m_p2;
	public float m_percent;


	public BotFunCall (String name, Object p1, String p2, float percent) {
		m_name = name;
		m_p1 = p1;
		m_p2 = p2;
		m_percent = percent;
	}

	public boolean eval (Entity e) {
		throw new RuntimeException("You shouldn't eval a BotFunCall");
	}
}