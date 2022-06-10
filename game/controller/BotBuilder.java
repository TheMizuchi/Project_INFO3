package controller;

import java.util.List;

import controller.action.Move;
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

import controller.Transition;

public class BotBuilder implements IVisitor{
	BotAutomata m_bot;
	IList m_states;

	public BotBuilder() {
		m_bot = new BotAutomata();
	}
	
	// IVisitor

	@Override
	public Object visit (Category cat) {
		System.out.println("Visit category");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit (Direction dir) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit (Key key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit (Value v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit (Underscore u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enter (FunCall funcall) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object exit (FunCall funcall, List<Object> parameters) {
		ICondition cond;
		IAction action;
		State state;
		switch (funcall.name) {
			case ("Move"):
				action = new Move((char) parameters.get(0));
			default:
				throw new RuntimeException("Action inconnue");
		}
		return new Transition(cond, action, state);
	}

	@Override
	public Object visit (BinaryOp operator, Object left, Object right) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit (UnaryOp operator, Object expression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit (State state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enter (Mode mode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object exit (Mode mode, Object source_state, Object behaviour) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit (Behaviour behaviour, List<Object> transitions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enter (Condition condition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object exit (Condition condition, Object expression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enter (Action action) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object exit (Action action, List<Object> funcalls) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit (Transition transition, Object condition, Object action, Object target_state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enter (Automaton automaton) {
		// TODO Auto-generated method stub
		
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
