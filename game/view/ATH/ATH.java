package view.ATH;

import java.awt.Color;
import java.awt.Graphics;

import model.entity.Entity;
import model.entity.J1;
import model.entity.J2;
import model.entity.Player;
import view.ViewElement;


public class ATH extends ViewElement {

	Entity j1, j2;
	HealthBar hpj1, hpj2;


	private ATH (Player j1, Player j2) {
		super(0, 0, 1);
		this.j1 = j1;
		this.j2 = j2;
		this.hpj1 = new HealthBar(50, 10, Color.red, J1.getInstance().getPv());
		this.hpj2 = new HealthBar(1370, 10, Color.blue, J2.getInstance().getPv());
	}


	private static ATH INSTANCE = null;


	public static ATH getInstance () {

		if (INSTANCE == null) {
			throw new RuntimeException("L'ATH n'a pas été instancié.");
		}
		return INSTANCE;
	}

	public static ATH getInstance (Player j1, Player j2) {

		if (INSTANCE == null) {
			INSTANCE = new ATH(j1, j2);
		}
		return INSTANCE;
	}

	@Override
	public void paint (Graphics g) {
		//hpj1.paint(g);
		//hpj2.paint(g);
		new HealthBar(50, 10, Color.red, J1.getInstance().getPv()).paint(g);
		new HealthBar(1300, 10, Color.blue, J2.getInstance().getPv()).paint(g);
		
	}
}
