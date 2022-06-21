package view.graphicEntity;

import model.entity.EntityInterface;
import view.animation.J2Animation;


public class J2View extends EntityView {

	J2Animation a;


	public J2View (EntityInterface e) {
		super(0, 0, 1, e, new J2Animation());
		this.a = (J2Animation) super.a;
		this.a.setDelay(150);
	}

	public void show () {
		this.a.show();
	}

	public void hide () {
		this.a.hide();
	}

	public void attack () {
		a.setListener(this.al);
		a.attack();
	}
}
