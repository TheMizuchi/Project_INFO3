package view.graphicEntity;

import model.entity.EntityInterface;
import view.animation.BatAnimation;
import view.animation.Animation.AnimationListener;

public class BatView extends EntityView {
	
	AnimationListener al;
	BatAnimation a;

	public BatView (EntityInterface e) {
		super(0, 0, 1, e, new BatAnimation());
		this.a = (BatAnimation) super.a;
	}
}
