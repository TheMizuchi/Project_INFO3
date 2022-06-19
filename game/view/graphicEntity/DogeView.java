package view.graphicEntity;

import model.entity.EntityInterface;
import view.animation.CowboyAnimation;
import view.animation.DogeAnimation;

public class DogeView extends EntityView{
	DogeAnimation a;


	public DogeView (EntityInterface e) {
		super(0, 0, 1, e, new CowboyAnimation());
		this.a = (DogeAnimation) super.a;
	}
}
