package view.graphicEntity;

import model.entity.EntityInterface;
import view.animation.ArcherAnimation;


public class ArcherView extends EntityView {

	ArcherAnimation a;


	public ArcherView (EntityInterface e) {
		super(0, 0, 1, e, new ArcherAnimation());
		this.a = (ArcherAnimation) super.a;
	}
}
