package controller;

import model.entity.Entity;

public interface ICondition {

	abstract boolean eval (Entity e);

}
