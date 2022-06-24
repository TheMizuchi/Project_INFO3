package view.graphicEntity;

import model.entity.EntityInterface;
import view.animation.StairsAnimation;


public class StairsView extends EntityView {

	StairsAnimation a;


	public StairsView (EntityInterface e) {
		super(0, 0, 1, e, new StairsAnimation());
		this.a = (StairsAnimation) super.a;
		this.a.setDelay(150);
	}

	@Override
	public void walk () {
		this.idle();
	}

	@Override
	public void attack () {
		return;
	}
}
