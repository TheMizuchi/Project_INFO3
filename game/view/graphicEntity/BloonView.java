package view.graphicEntity;

import model.entity.EntityInterface;
import view.animation.BloonAnimation;


public class BloonView extends EntityView {

	BloonAnimation a;


	public BloonView (EntityInterface e) {
		super(0, 0, 1, e, new BloonAnimation());
		this.a = (BloonAnimation) super.a;
		this.a.setDelay(50);
	}

	public void explode () {
		a.setListener(this.al);
		this.a.explode();
	}
}
