package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;


public class Victory extends JFrame {

	JLabel label;


	public Victory () {

		Border border = BorderFactory.createLineBorder(Color.YELLOW, 3);

		label = new JLabel();

		label.setForeground(Color.RED);
		label.setBackground(Color.orange);
		label.setVerticalAlignment(JLabel.CENTER);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setText("Victory !");
		label.setFont(new Font("Comic sans", Font.BOLD, 27));
		label.setBorder(border);

		this.setTitle("Game Over !"); // sets title of this frame.
		this.setDefaultCloseOperation(HIDE_ON_CLOSE); // Gère le bouton croix de la fenetre.
		this.setSize(600, 600); //Set the size of the frame.
		this.setVisible(true); // make it visible.

		this.add(label);
	}


	private static Victory INSTANCE = null;


	public static Victory getInstance () {

		if (INSTANCE == null) {
			INSTANCE = new Victory();
		}
		return INSTANCE;
	}

}