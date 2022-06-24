package view.graphicEntity;

import model.entity.EntityInterface;
import view.animation.DogeAnimation;


public class DogeView extends EntityView {

	DogeAnimation a;


	public DogeView (EntityInterface e) {
		super(0, 0, 1, e, new DogeAnimation());
		this.a = (DogeAnimation) super.a;
	}

	public void getAngry () {
		this.a.setListener(this.al);
		this.a.angry();
	}
}
