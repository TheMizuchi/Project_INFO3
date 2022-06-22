package view.graphicEntity;

import view.animation.BloonAnimation;

public class DoorView extends EntityView {
	
	DoorAnimation a;
	
	public Doorview(EntityInterface e) {
		super(0, 0, 1, e, new DoorAnimation());
		this.a = (DoorAnimation) super.a;
		this.a.setDelay(50);
		
	}

}
