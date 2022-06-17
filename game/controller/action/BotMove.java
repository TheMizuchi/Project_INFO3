package controller.action;

import controller.BotAction;
import controller.Controller;
import model.entity.Direction;
import model.entity.Entity;
import model.entity.EntityAbsoluteDirection;
import model.entity.EntityRelativeDirection;
import model.entity.PlayerAbsoluteDirection;
import model.entity.PlayerRelativeDirection;
import model.entity.TypeEntity;


public class BotMove extends BotAction {

	String m_s;


	public BotMove (String s) {
		m_s = s;
	}

	@Override
	public boolean apply (Entity e) {
		int type = e.getType();

		switch (m_s) {
			case "N":
				if (type == TypeEntity.Allié) {
					e.move(new PlayerAbsoluteDirection(Math.PI / 2));
				} else {
					e.move(new EntityAbsoluteDirection(Math.PI / 2));
				}
				break;
			case "W":
				if (type == TypeEntity.Allié) {
					e.move(new PlayerAbsoluteDirection(Math.PI));
				} else {
					e.move(new EntityAbsoluteDirection(Math.PI));
				}
				break;
			case "S":
				if (type == TypeEntity.Allié) {
					e.move(new PlayerAbsoluteDirection(3 * Math.PI / 2));
				} else {
					e.move(new EntityAbsoluteDirection(3 * Math.PI / 2));
				}
				break;
			case "E":
				if (type == TypeEntity.Allié) {
					e.move(new PlayerAbsoluteDirection(0));
				} else {
					e.move(new EntityAbsoluteDirection(0));
				}
				break;
			case "NW":
				if (type == TypeEntity.Allié) {
					e.move(new PlayerRelativeDirection(e, 3 * Math.PI / 4));
				} else {
					e.move(new EntityRelativeDirection(e, 3 * Math.PI / 4));
				}
				break;
			case "NE":
				if (type == TypeEntity.Allié) {
					e.move(new PlayerRelativeDirection(e, Math.PI / 4));
				} else {
					e.move(new EntityRelativeDirection(e, Math.PI / 4));
				}
				break;
			case "SE":
				if (type == TypeEntity.Allié) {
					e.move(new PlayerRelativeDirection(e, 7 * Math.PI / 4));
				} else {
					e.move(new EntityRelativeDirection(e, 7 * Math.PI / 4));
				}
				break;
			case "SW":
				if (type == TypeEntity.Allié) {
					e.move(new PlayerRelativeDirection(e, 5 * Math.PI / 4));
				} else {
					e.move(new EntityRelativeDirection(e, 5 * Math.PI / 4));
				}
				break;
			case "F":
				if (type == TypeEntity.Allié) {
					e.move(new PlayerRelativeDirection(e, 0));
				} else {
					e.move(new EntityRelativeDirection(e, 0));
				}
				break;
			case "L":
				if (type == TypeEntity.Allié) {
					e.move(new PlayerRelativeDirection(e, Math.PI / 2));
				} else {
					e.move(new EntityRelativeDirection(e, Math.PI / 2));
				}
				break;
			case "B":
				if (type == TypeEntity.Allié) {
					e.move(new PlayerRelativeDirection(e, Math.PI));
				} else {
					e.move(new EntityRelativeDirection(e, Math.PI));
				}
				break;
			case "R":
				if (type == TypeEntity.Allié) {
					e.move(new PlayerRelativeDirection(e, 3 * Math.PI / 2));
				} else {
					e.move(new EntityRelativeDirection(e, 3 * Math.PI / 2));
				}
				break;
			default:
				break;
		}
		return true;
	}

}
