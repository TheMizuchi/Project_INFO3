package view.graphicEntity;

import java.awt.Graphics;

import model.entity.EntityInterface;
import view.Viewport;
import view.animation.Animation;
import view.animation.CowboyAnimation;
import view.animation.Animation.AnimationListener;
/*
 * Cette classe sert à définir le visuel de l'entité. Elle doit définir quelles
 * animations ou quelles images fixes afficher mais ne s'occupe pas de les
 * afficher elle même C'est le rôle de la classe [EntityName]Animation
 */


public class CowboyView extends EntityView {

	CowboyAnimation a;


	public CowboyView (EntityInterface e) {
		super(0, 0, 1, e, new CowboyAnimation());
		this.a = (CowboyAnimation) super.a;
	}

	public void spin () {
		this.a.setListener(this.al);
		this.a.spin();
	}
}
