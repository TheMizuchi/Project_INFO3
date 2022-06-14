package controller;

import java.util.List;

import controller.action.*;
import controller.condition.*;
import controller.condition.tree.*;
import edu.polytech.oop.collections.IList;
import edu.polytech.oop.collections.LinkedList;
import info3.game.automata.ast.*;

// Pour l'instant le builder ne gére qu'une seule action par transition et ne
// gére pas les probas


public class BotBuilder implements IVisitor {

	private BotAutomata m_bot;


	public BotAutomata getAutomata () {
		return m_bot;
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
		ICondition cond = null;
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

		switch (funcall.name) {
			case "True":
				cond = new BotTrue();
				break;
			case "Key":
				cond = new BotKey(p1);
				break;
			case "Cell":
				cond = new BotCell(p1, p2);
			default:
		}
		return cond;
	}

	@Override
	public Object visit (BinaryOp operator, Object left, Object right) {

		switch (operator.operator) {
			case ("&"):
				return new BotAndOp((ICondition) left, (ICondition) right);
			case ("/"):
				return new BotOrOp((ICondition) left, (ICondition) right);
			default:
				throw new RuntimeException("Un singe aurait mieux fait");
		}
	}

	@Override
	public Object visit (UnaryOp operator, Object expression) {

		switch (operator.operator) {
			case ("!"):
				return new BotNotOp((ICondition) expression);
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
		//		condition.toString();
		//		ICondition cond = null;
		//		String[] str = condition.toString().split("%");
		//		str[1].split("\\(");
		//		List<String> l = (List<String>) expression;
		//
		//		switch (str[1].split("\\(")[0]) {
		//			case "True":
		//				cond = new BotTrue();
		//				break;
		//			case "Key":
		//				cond = new BotKey(l.get(0).charAt(0));
		//				break;
		//			case "Cell":
		//				cond = new BotCell();
		//				break;
		//			default:
		//				throw new RuntimeException("NYI");
		//		}
		//		return cond;
		return expression;
	}

	@Override
	public void enter (Action action) {}

	@Override
	public Object exit (Action action, List<Object> funcalls) {
		IAction act = null; // Devra être une liste d'action plus tard

		if (action.calls.isEmpty()) {
			act = new BotNone();
		} else {

			for (FunCall call : action.calls) {

				switch (call.name) {
					case ("Move"):
						if (call.parameters.size() == 0) {
							act = new BotMove("F");
						} else {
							act = new BotMove(call.parameters.get(0).toString());
						}
						break;
					case ("Pop"):
						act = new BotPop();
						break;
					case ("Wizz"):
						act = new BotWizz();
						break;
					default:
						throw new RuntimeException("NYI");
				}
				break; // On ne gére qu'une action par transition pour le moment
			}
		}
		return act;
	}

	@Override
	public Object visit (Transition transition, Object condition, Object action, Object target_state) {
		return new BotTransition((ICondition) condition, (IAction) action, (BotState) target_state);
	}

	@Override
	public void enter (Automaton automaton) {
		m_bot = new BotAutomata(automaton.toString());
	}

	@Override
	public Object exit (Automaton automaton, Object initial_state, List<Object> modes) {
		m_bot.m_current_state = (BotState) initial_state;
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
