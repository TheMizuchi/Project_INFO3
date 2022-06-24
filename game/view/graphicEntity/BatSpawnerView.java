package view.graphicEntity;

import model.entity.EntityInterface;
import view.animation.BatSpawnerAnimation;


public class BatSpawnerView extends EntityView {

	BatSpawnerAnimation as;


	public BatSpawnerView (EntityInterface e) {
		super(0, 0, 1, e, new BatSpawnerAnimation());
		this.a = (BatSpawnerAnimation) super.a;
	}

	@Override
	public void attack () {
		return;
	}
}
