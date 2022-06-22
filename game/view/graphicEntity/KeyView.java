package view.graphicEntity;

import model.entity.EntityInterface;
import view.animation.KeyAnimation;


public class KeyView extends EntityView {

	KeyAnimation a;


	public KeyView (EntityInterface e) {
		super(0, 0, 1, e, new KeyAnimation());
		this.a = (KeyAnimation) super.a;
		this.a.setDelay(150);
	}

}
