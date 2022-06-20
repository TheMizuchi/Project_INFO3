package view.ATH;

import java.awt.Color;
import java.awt.Graphics;

import model.entity.Entity;
import view.ViewElement;


public class ATH extends ViewElement {

	Entity j1, j2;
	HealthBar hpj1, hpj2;


	private ATH (Entity j1, Entity j2) {
		super(0, 0, 1);
		this.j1 = j1;
		this.j2 = j2;
		this.hpj1 = new HealthBar(260, 10, Color.red);
		this.hpj2 = new HealthBar(1160, 10, Color.blue);
	}


	private static ATH INSTANCE = null;


	public static ATH getInstance () {

		if (INSTANCE == null) {
			throw new RuntimeException("L'ATH n'a pas été instancié.");
		}
		return INSTANCE;
	}

	public static ATH getInstance (Entity j1, Entity j2) {

		if (INSTANCE == null) {
			INSTANCE = new ATH(j1, j2);
		}
		return INSTANCE;
	}

	@Override
	public void paint (Graphics g) {
		hpj1.paint(g);
		hpj2.paint(g);
	}
}
