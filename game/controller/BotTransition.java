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

	public BotState eval () {

		/*
		 * Si la condition est une linkedList // traitement des conditions
		 * ICollection.Iterator iter = m_cond.iterator(); while (iter.hasNext()) {
		 * ICondition condition = (ICondition) iter.next(); if (!condition.eval())
		 * return null; } // si les conditions sont justes, traitement des actions iter
		 * = m_actions.iterator(); while (iter.hasNext()) { IAction action = (IAction)
		 * iter.next(); action.apply(); } return m_target;
		 */

		if (m_cond.eval()) {
			ICollection.Iterator iter = m_actions.iterator();

			while (iter.hasNext()) {
				IAction action = (IAction) iter.next();
				action.apply();
			}
			return m_target;
		}
		return null;
	}

}
