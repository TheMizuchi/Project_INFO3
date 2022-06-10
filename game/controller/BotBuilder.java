package controller;

import java.util.List;

import controller.action.BotMove;
import controller.condition.BotTrue;
import edu.polytech.oop.collections.IList;
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

	BotAutomata m_bot;
	IList m_states;


	public BotBuilder () {
		m_bot = new BotAutomata();
	}

	// IVisitor

	@Override
	public Object visit (Category cat) {
		return null;
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
		return null;
	}

	@Override
	public void enter (FunCall funcall) {}

	@Override
	public Object exit (FunCall funcall, List<Object> parameters) {
		switch (funcall.name.toString()) {
			case ("Move"):
				return new BotMove((String) parameters.get(0));
			case ("True"):
				return new BotTrue();
			default:
				throw new RuntimeException("Action inconnue");
		}
	}

	@Override
	public Object visit (BinaryOp operator, Object left, Object right) {
		return null;
	}

	@Override
	public Object visit (UnaryOp operator, Object expression) {
		return null;
	}

	@Override
	public Object visit (State state) {
		BotState s = new BotState();
		m_bot.add_state(s);
		return s;
	}

	@Override
	public void enter (Mode mode) {
	}

	@Override
	public Object exit (Mode mode, Object source_state, Object behaviour) {
		return null;
	}

	@Override
	public Object visit (Behaviour behaviour, List<Object> transitions) {
		return null;
	}

	@Override
	public void enter (Condition condition) {
	}

	@Override
	public Object exit (Condition condition, Object expression) {
		return null;
	}

	@Override
	public void enter (Action action) {
	}

	@Override
	public Object exit (Action action, List<Object> funcalls) {
		return null;
	}

	@Override
	public Object visit (Transition transition, Object condition, Object action, Object target_state) {
		return null;
	}

	@Override
	public void enter (Automaton automaton) {
	}

	@Override
	public Object exit (Automaton automaton, Object initial_state, List<Object> modes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit (AST bot, List<Object> automata) {
		// TODO Auto-generated method stub
		return null;
	}

}
