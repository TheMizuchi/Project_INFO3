package view.graphicEntity;

import model.entity.EntityInterface;
import view.animation.CowboyAnimation;


public class CowboyView extends EntityView {

	CowboyAnimation a;


	public CowboyView (EntityInterface e) {
		super(0, 0, 1, e, new CowboyAnimation());
		this.a = (CowboyAnimation) super.a;
	}

	public void spin () {
		this.a.setListener(this.al);
		this.a.spin();
	}

	@Override
	public void attack () {
		return;
	}
}
