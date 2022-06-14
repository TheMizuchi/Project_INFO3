package controller;

import java.util.List;

import controller.action.*;
import controller.condition.*;
import controller.condition.tree.*;
import edu.polytech.oop.collections.IList;
import edu.polytech.oop.collections.LinkedList;
import info3.game.automata.ast.*;


public class BotBuilder implements IVisitor {

	/** Constructeur privé */
	private BotBuilder () {}


	/** Instance unique non préinitialisée */
	private static BotBuilder INSTANCE = null;


	/** Point d'accès pour l'instance unique du singleton */
	public static BotBuilder getInstance () {

		if (INSTANCE == null) {
			INSTANCE = new BotBuilder();
		}
		return INSTANCE;
	}


	private BotAutomata m_bot;


	private ICondition getCond (Object expression) {

		if (expression instanceof BotFunCall) {
			BotFunCall fc = (BotFunCall) expression;
			ICondition cond = null;

			switch (fc.m_name) {
				case "True":
					cond = new BotTrue();
					break;
				case "Key":
					cond = new BotKey(fc.m_p1);
					break;
				case "Cell":
					cond = new BotCell(fc.m_p1, fc.m_p2);
				default:
			}
			return cond;
		} else {
			return (ICondition) expression;
		}
	}

	// IVisitor

	@Override
	public Object visit (Category cat) {
		return cat.toString();
	}

	@Override
	public Object visit (Direction dir) {
		return dir.toString();
	}

	@Override
	public Object visit (Key key) {
		return key.toString();
	}

	@Override
	public Object visit (Value v) {
		return v.toString();
	}

	@Override
	public Object visit (Underscore u) {
		throw new RuntimeException("Underscore NYI");
	}

	@Override
	public void enter (FunCall funcall) {}

	@Override
	public Object exit (FunCall funcall, List<Object> parameters) {
		String p1 = "";
		String p2 = "";

		switch (parameters.size()) {
			case 2:
				p2 = (String) parameters.get(1);
			case 1:
				p1 = (String) parameters.get(0);
			default:
				break;
		}
		return new BotFunCall(funcall.name, p1, p2, funcall.percent);

	}

	@Override
	public Object visit (BinaryOp operator, Object left, Object right) {
		ICondition lcond = getCond(left);
		ICondition rcond = getCond(right);

		switch (operator.operator) {
			case ("&"):
				return new BotAndOp(lcond, rcond);
			case ("/"):
				return new BotOrOp(lcond, rcond);
			default:
				throw new RuntimeException("Un singe aurait mieux fait");
		}
	}

	@Override
	public Object visit (UnaryOp operator, Object expression) {

		switch (operator.operator) {
			case ("!"):
				return new BotNotOp(getCond(expression));
			default:
				throw new RuntimeException("Un singe aurait mieux fait");
		}
	}

	@Override
	public Object visit (State state) {
		return m_bot.add_state(state.name);
	}

	@Override
	public void enter (Mode mode) {}

	@Override
	public Object exit (Mode mode, Object source_state, Object behaviour) {
		IList transitions = (LinkedList) behaviour;
		IList.Iterator ite = transitions.iterator();

		while (ite.hasNext()) {
			((BotState) source_state).add_transition((BotTransition) ite.next());
		}
		return null;
	}

	@Override
	public Object visit (Behaviour behaviour, List<Object> transitions) {
		IList trans = new LinkedList();

		for (Object transition : transitions) {
			trans.insertAt(trans.length(), (BotTransition) transition);
		}
		return trans;
	}

	@Override
	public void enter (Condition condition) {}

	@Override
	public Object exit (Condition condition, Object expression) {
		return getCond(expression);
	}

	@Override
	public void enter (Action action) {}

	@Override
	public Object exit (Action action, List<Object> funcalls) {
		IList actions = new LinkedList();

		IAction act = null;

		if (action.calls.isEmpty()) {
			actions.insertAt(0, new BotNone());
		} else {

			for (Object obj : funcalls) {
				BotFunCall call = (BotFunCall) obj;

				switch (call.m_name) {
					case "Move":
						act = new BotMove(call.m_p1);
						break;
					case "Pop":
						act = new BotPop(call.m_p1);
						break;
					case "Hit":
						act = new BotHit(call.m_p1);
						break;
					case "Power":
						act = new BotPower();
						break;
					case "Jump":
						act = new BotJump();
						break;
				}
				actions.insertAt(0, act);
			}
		}
		return actions;
	}

	@Override
	public Object visit (Transition transition, Object condition, Object actions, Object target_state) {
		return new BotTransition((ICondition) condition, (IList) actions, (BotState) target_state);
	}

	@Override
	public void enter (Automaton automaton) {
		m_bot = new BotAutomata(automaton.toString());
	}

	@Override
	public Object exit (Automaton automaton, Object initial_state, List<Object> modes) {
		m_bot.m_initial_state = (BotState) initial_state;
		return m_bot;
	}

	@Override
	public Object visit (AST bot, List<Object> automata) {
		IList bots = new LinkedList();

		for (Object aut : automata) {
			bots.insertAt(bots.length(), (BotAutomata) aut);
		}
		return bots;
	}

}
