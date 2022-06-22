package controller;

import java.util.List;

import controller.action.BotEgg;
import controller.action.BotExplode;
import controller.action.BotGet;
import controller.action.BotHit;
import controller.action.BotJump;
import controller.action.BotMove;
import controller.action.BotNone;
import controller.action.BotPick;
import controller.action.BotPop;
import controller.action.BotPower;
import controller.action.BotProtect;
import controller.action.BotStore;
import controller.action.BotThrow;
import controller.action.BotTurn;
import controller.action.BotWait;
import controller.action.BotWizz;
import controller.condition.BotCell;
import controller.condition.BotClosest;
import controller.condition.BotGotPower;
import controller.condition.BotGotStuff;
import controller.condition.BotKey;
import controller.condition.BotMyDir;
import controller.condition.BotTrue;
import controller.condition.tree.BotAndOp;
import controller.condition.tree.BotFunCall;
import controller.condition.tree.BotNotOp;
import controller.condition.tree.BotOrOp;
import edu.polytech.oop.collections.IList;
import edu.polytech.oop.collections.LinkedList;
import info3.game.automata.ast.AST;
import info3.game.automata.ast.Action;
import info3.game.automata.ast.Automaton;
import info3.game.automata.ast.Behaviour;
import info3.game.automata.ast.BinaryOp;
import info3.game.automata.ast.Category;
import info3.game.automata.ast.Condition;
import info3.game.automata.ast.Direction;
import info3.game.automata.ast.FunCall;
import info3.game.automata.ast.IVisitor;
import info3.game.automata.ast.Key;
import info3.game.automata.ast.Mode;
import info3.game.automata.ast.State;
import info3.game.automata.ast.Transition;
import info3.game.automata.ast.UnaryOp;
import info3.game.automata.ast.Underscore;
import info3.game.automata.ast.Value;


public class BotBuilder implements IVisitor {

	private BotBuilder () {}


	private static BotBuilder INSTANCE = null;


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
			Object p1 = fc.m_p1;

			if (p1 == null) {
				p1 = new BotDirection("");
			}
			ICondition cond = null;

			switch (fc.m_name) {
				case "True":
					cond = new BotTrue();
					break;
				case "Key":
					cond = new BotKey((String) p1);
					break;
				case "Cell":
					cond = new BotCell((BotDirection) p1, fc.m_p2);
					break;
				case "MyDir":
					cond = new BotMyDir((BotDirection) p1);
					break;
				case "Closest":
					cond = new BotClosest((String) p1, fc.m_p2);
					break;
				case "GotPower":
					cond = new BotGotPower();
					break;
				case "GotStuff":
					cond = new BotGotStuff();
					break;
				default:
					throw new RuntimeException("Condition inexistante");
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
		return new BotDirection(dir.toString());
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
		throw new RuntimeException("NYI");
	}

	@Override
	public void enter (FunCall funcall) {}

	@Override
	public Object exit (FunCall funcall, List<Object> parameters) {
		Object p1 = null;
		String p2 = "";

		switch (parameters.size()) {
			case 2:
				p2 = (String) parameters.get(1);
			case 1:
				p1 = (Object) parameters.get(0);
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

		BotAction act = null;

		if (action.calls.isEmpty()) {
			act = new BotNone();
			act.setPercent(100);
			actions.insertAt(0, act);
		} else {
			float sum = 0; 							// Somme des probas différentes de -1
			IList toSetProba = new LinkedList(); 	// Stocke les action d'une proba de -1 (à affecter)

			BotDirection p1 = null;

			for (Object obj : funcalls) {
				BotFunCall call = (BotFunCall) obj;
				p1 = (BotDirection) call.m_p1;

				if (p1 == null) {
					p1 = new BotDirection("");
				}

				switch (call.m_name) {
					case "Move":
						act = new BotMove(p1);
						break;
					case "Pop":
						act = new BotPop();
						break;
					case "Wizz":
						act = new BotWizz();
						break;
					case "Hit":
						act = new BotHit(p1);
						break;
					case "Power":
						act = new BotPower();
						break;
					case "Jump":
						act = new BotJump();
						break;
					case "Turn":
						act = new BotTurn(p1);
						break;
					case "Protect":
						act = new BotProtect();
						break;
					case "Pick":
						act = new BotPick();
						break;
					case "Throw":
						act = new BotThrow(p1);
						break;
					case "Store":
						act = new BotStore();
						break;
					case "Get":
						act = new BotGet();
						break;
					case "Explode":
						act = new BotExplode();
						break;
					case "Egg":
						act = new BotEgg();
						break;
					case "Wait":
						act = new BotWait();
						break;
					case "None":
						act = new BotNone();
						break;
					default:
						throw new RuntimeException("Action inexistante");
				}

				if (call.m_percent != -1) {
					sum += call.m_percent;
					act.setPercent(call.m_percent);
				} else {
					toSetProba.insertAt(0, act);
				}
				actions.insertAt(actions.length(), act);

			}

			double probaToSet = (100 - sum) / (double) toSetProba.length();
			IList.Iterator ite = toSetProba.iterator();

			while (ite.hasNext()) {
				((BotAction) ite.next()).setPercent(probaToSet);
				sum += probaToSet;
			}

			if (sum > 100) {
				throw new RuntimeException("L'automate est incorrecte (Une transition a une somme d'action d'une probabilité supérieur à 100)");
			} else if (sum < 100) {
				throw new RuntimeException("La somme des proba ne fait pas 100 sur une transition");
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
