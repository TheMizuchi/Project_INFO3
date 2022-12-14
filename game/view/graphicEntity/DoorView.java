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

	public void closing () {
		this.a.setListener(this.al);
		this.a.closing();
	}

	public void opened () {
		this.a.setListener(this.al);
		this.a.walk();
	}

	public void lat_idle () {
		this.a.setListener(this.al);
		this.a.opening();
	}

	public void lat_opening () {
		this.a.setListener(this.al);
		this.a.opening();
	}

	public void lat_closing () {
		this.a.setListener(this.al);
		this.a.opening();
	}

	public void lat_opened () {
		this.a.setListener(this.al);
		this.a.opening();
	}

	@Override
	public void attack () {
		return;
	}

}
