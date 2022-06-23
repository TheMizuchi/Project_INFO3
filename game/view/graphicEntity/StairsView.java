package view.graphicEntity;

import model.entity.EntityInterface;
import view.animation.StairsAnimation;


public class StairsView extends EntityView {

	public StairsView (EntityInterface e) {
		super(0, 0, 1, e, new StairsAnimation());
		// TODO Auto-generated constructor stub
	}

}
