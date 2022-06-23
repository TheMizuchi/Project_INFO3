package view.graphicEntity;

import model.entity.EntityInterface;
import view.animation.J1Animation;
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
	
	public void show () {
		this.a.show();
	}

	public void hide () {
		this.a.hide();
	}
}
