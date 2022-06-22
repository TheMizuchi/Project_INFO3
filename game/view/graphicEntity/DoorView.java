package view.graphicEntity;

import model.entity.EntityInterface;
import view.animation.DoorAnimation;


public class DoorView extends EntityView {

	DoorAnimation a;


	public DoorView (EntityInterface e) {
		super(0, 0, 1, e, new DoorAnimation());
		this.a = (DoorAnimation) super.a;
		this.a.setDelay(50);

	}

	public void opening () {
		this.a.setListener(this.al);
		this.a.opening();
	}

}
