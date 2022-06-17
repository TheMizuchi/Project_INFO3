package view.graphicEntity;

import model.entity.EntityInterface;
import view.animation.SkeletonAnimation;

public class SkeletonView extends EntityView {

	SkeletonAnimation a;


	public SkeletonView (EntityInterface e) {
		super(0, 0, 1, e, new SkeletonAnimation());
		this.a = (SkeletonAnimation) super.a;
		this.a.setDelay(50);
	}

	public void attack () {
		a.setListener(this.al);
		a.attack();
	}
}
