package view.graphicEntity;

import model.entity.EntityInterface;
import view.animation.TorchAnimation;


public class TorchView extends EntityView {

	TorchAnimation a;


	public TorchView (EntityInterface e) {
		super(0, 0, 1, e, new TorchAnimation());
		this.a = (TorchAnimation) super.a;
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
