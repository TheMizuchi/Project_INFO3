package view.graphicEntity;

import model.entity.EntityInterface;
import view.animation.ArrowAnimation;


public class ArrowView extends EntityView {

	ArrowAnimation a;


	public ArrowView (EntityInterface e) {
		super(0, 0, 1, e, new ArrowAnimation());
		this.a = (ArrowAnimation) super.a;
		this.a.setDelay(50);
	}
	
	public void hautgauche () {
		a.setListener(this.al);
		a.hautgauche();
	}
	
	public void haut () {
		a.setListener(this.al);
		a.haut();
	}
	
	public void hautdroite () {
		a.setListener(this.al);
		a.hautdroite();
	}
	
	public void basgauche () {
		a.setListener(this.al);
		a.basgauche();
	}
	
	public void bas () {
		a.setListener(this.al);
		a.bas();
	}
	
	public void basdroite () {
		a.setListener(this.al);
		a.basdroite();
	}

	@Override
	public void attack () {		
	}
}
