package model.entity.behavior;

import model.Model;
import model.entity.Arrow;
import model.entity.Entity;
import model.entity.EntityProperties;
import model.entity.Vector;
import view.graphicEntity.EntityView;


public class J2NormalGroundBehavior extends PlayerNormalGroundBehavior {

	public J2NormalGroundBehavior (Entity e, EntityView ev) {
		super(e, ev);
	}

	@Override
	public void hit (Vector vec) {
		Model model = Model.getInstance();
		double eWidth = this.e.getProperties().getWidth();
		double eHeight = this.e.getProperties().getHeight();
		double aWidth = EntityProperties.ARROW.getWidth();
		double aHeight = EntityProperties.ARROW.getHeight();
		double eSemi_diagonal = 0.5 * Math.sqrt(eWidth * eWidth + eHeight * eHeight);
		double aSemi_diagonal = 0.5 * Math.sqrt(aWidth * aWidth + aHeight * aHeight);

		double eCenterX = this.e.getHitbox().getCenterRealX();
		double eCenterY = this.e.getHitbox().getCenterRealY();
		double aCenterX = eCenterX + (eSemi_diagonal + aSemi_diagonal + 0.1) * Math.cos(vec.getAngle());
		double aCenterY = eCenterY + (eSemi_diagonal + aSemi_diagonal + 0.1) * Math.sin(-vec.getAngle());
		double aX = aCenterX - aWidth / 2;
		double aY = aCenterY - aHeight / 2;

		Arrow a = (Arrow) model.createEntity(aX, aY, EntityProperties.ARROW);
		a.setVector(vec);
	}

}
