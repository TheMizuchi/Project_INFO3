package view.graphicEntity;

import model.entity.EntityInterface;
import view.animation.J1Animation;


public class J1View extends EntityView {

	J1Animation a;


	public J1View (EntityInterface e) {
		super(0, 0, 1, e, new J1Animation());
		this.a = (J1Animation) super.a;
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
