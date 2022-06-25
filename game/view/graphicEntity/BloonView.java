package view.graphicEntity;

import model.entity.EntityInterface;
import view.animation.BloonAnimation;


public class BloonView extends EntityView {

	BloonAnimation a;
	int level;


	public BloonView (EntityInterface e, int level) {
		super(0, 0, 1, e, new BloonAnimation());
		this.a = (BloonAnimation) super.a;
		this.a.setDelay(50);
		this.setLevel(level);
		this.level = level;
	}

	public void explode () {
		a.setListener(this.al);
		this.a.explode();
	}

	public void setLevel (int n) {
		if (n < 0 || n > 6)
			throw new RuntimeException("Bloon entity created with a wrong level");
		this.a.setLevel(n);
	}

	@Override
	public void attack () {
		return;
	}
	
	@Override
	protected void setPosition (int x, int y, double scale) {
		super.setPosition(x, y, scale + level/3);
	}
}
