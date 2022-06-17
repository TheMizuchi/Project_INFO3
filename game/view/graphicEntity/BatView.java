package view.graphicEntity;

import model.entity.EntityInterface;
import view.animation.BatAnimation;


public class BatView extends EntityView {

	BatAnimation a;


	public BatView (EntityInterface e) {
		super(0, 0, 1, e, new BatAnimation());
		this.a = (BatAnimation) super.a;
	}
}
