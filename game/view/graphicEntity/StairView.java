package view.graphicEntity;

import model.entity.EntityInterface;
import view.animation.StairAnimation;


public class StairView extends EntityView {

	StairAnimation a;


	public StairView (EntityInterface e) {
		super(0, 0, 1, e, new StairAnimation());
		this.a = (StairAnimation) super.a;
		this.a.setDelay(150);
	}

	@Override
	public void walk () {
		this.idle();
	}
}
