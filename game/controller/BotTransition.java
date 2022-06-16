package controller;

import edu.polytech.oop.collections.ICollection;
import edu.polytech.oop.collections.IList;
import model.entity.Entity;


public class BotTransition {

	ICondition m_cond;
	IList m_actions;
	BotState m_target;


	public BotTransition (ICondition cond, IList actions, BotState target) {
		m_cond = cond;
		m_actions = actions;
		m_target = target;
	}

	public BotState eval (Entity e) {
		double proba = 100 * Math.random();

		if (m_cond.eval()) {
			ICollection.Iterator iter = m_actions.iterator();
			BotAction act;

			while (iter.hasNext()) {
				act = (BotAction) iter.next();

				if (proba <= act.m_percent) {
					act.apply(e);
					break;
				} else {
					proba -= act.m_percent;
				}
			}
			return m_target;
		}
		return null;
	}

}
