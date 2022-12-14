package controller;

import edu.polytech.oop.collections.ICollection;
import edu.polytech.oop.collections.IList;


public class BotTransition {

	ICondition m_cond;
	IList m_actions;
	BotState m_target;


	public BotTransition (ICondition cond, IList actions, BotState target) {
		m_cond = cond;
		m_actions = actions;
		m_target = target;
	}

	public BotState eval (RefAutomata aut) {
		double proba = 100 * Math.random();

		if (m_cond.eval(aut.m_e)) {
			ICollection.Iterator iter = m_actions.iterator();
			BotAction act;

			while (iter.hasNext()) {
				act = (BotAction) iter.next();

				if (proba <= act.m_percent) {
					act.apply(aut.m_e, aut);
					break;
				} else {
					proba -= act.m_percent;
				}
			}

			if (m_target != null && m_target.m_name.equals("")) {
				aut.m_e.deleteEntity();
			}
			return m_target;
		}
		return null;
	}

}
