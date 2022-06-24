package view.graphicEntity;

import model.entity.EntityInterface;
import view.animation.MysteryAnimation;


public class MysteryView extends EntityView {

	MysteryAnimation a;


	public MysteryView (EntityInterface e) {
		super(0, 0, 1, e, new MysteryAnimation());
		this.a = (MysteryAnimation) super.a;
	}

	@Override
	public void attack () {
		return;
	}

}
