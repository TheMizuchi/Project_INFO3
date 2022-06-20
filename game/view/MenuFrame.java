package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MenuFrame extends JFrame implements ActionListener {
	
	JButton but_J1;
	JButton but_J2;
	JButton but_Bloon;
	JButton but_Bat;
	JButton but_Skeleton;
	
	JLabel label;
	
	public MenuFrame(){
		
		label = new JLabel();
		
		but_J1 = new JButton();
		but_J2 = new JButton();
		but_Bloon = new JButton();
		but_Bat = new JButton();
		but_Skeleton = new JButton();
		
		
		but_J1.setBounds(250, 0, 100, 30);
		but_J2.setBounds(250, 30, 100, 30);
		but_Bloon.setBounds(250, 60, 100, 30);
		but_Bat.setBounds(250, 90, 100, 30);
		but_Skeleton.setBounds(250, 120, 100, 30);
		
		
		but_J1.addActionListener(this);
		//button.addActionListener(e -> System.out.println("BOUTON !"));
		but_J2.addActionListener(this);
		but_Bloon.addActionListener(this);
		but_Bat.addActionListener(this);
		but_Skeleton.addActionListener(this);
		
		
		but_J1.setText("Le J1");
		but_J2.setText("Le J2");
		but_Bloon.setText("Le Ballon");
		but_Bat.setText("La chauve-souris");
		but_Skeleton.setText("Le squelette");
		
		
		but_J1.setFocusable(false);
		but_J2.setFocusable(false);
		but_Bloon.setFocusable(false);
		but_Bat.setFocusable(false);
		but_Skeleton.setFocusable(false);
		
		but_J1.setHorizontalTextPosition(JButton.CENTER);
		but_J1.setVerticalTextPosition(JButton.CENTER);
		but_J2.setHorizontalTextPosition(JButton.CENTER);
		but_J2.setVerticalTextPosition(JButton.CENTER);
		but_Bloon.setHorizontalTextPosition(JButton.CENTER);
		but_Bloon.setVerticalTextPosition(JButton.CENTER);
		but_Bat.setHorizontalTextPosition(JButton.CENTER);
		but_Bat.setVerticalTextPosition(JButton.CENTER);
		but_Skeleton.setHorizontalTextPosition(JButton.CENTER);
		but_Skeleton.setVerticalTextPosition(JButton.CENTER);
		
		
		but_J1.setFont(new Font("Comic sans", Font.BOLD, 13));
		but_J1.setForeground(Color.BLACK);
		but_J1.setBackground(new Color(163,76,0));
		but_J1.setBorder(BorderFactory.createBevelBorder(ABORT)); //Permet de custom les bords du button.
		//button.setEnabled(false); Desactive le button. Si on le met dans le if, alors on peut le cliquer une seul fois.
		but_J2.setFont(new Font("Comic sans", Font.BOLD, 13));
		but_J2.setForeground(Color.BLACK);
		but_J2.setBackground(new Color(163,76,0));
		but_J2.setBorder(BorderFactory.createBevelBorder(ABORT));
		
		but_Bloon.setFont(new Font("Comic sans", Font.BOLD, 13));
		but_Bloon.setForeground(Color.BLACK);
		but_Bloon.setBackground(new Color(163,76,0));
		but_Bloon.setBorder(BorderFactory.createBevelBorder(ABORT));
		
		but_Bat.setFont(new Font("Comic sans", Font.BOLD, 13));
		but_Bat.setForeground(Color.BLACK);
		but_Bat.setBackground(new Color(163,76,0));
		but_Bat.setBorder(BorderFactory.createBevelBorder(ABORT));
		
		but_Skeleton.setFont(new Font("Comic sans", Font.BOLD, 13));
		but_Skeleton.setForeground(Color.BLACK);
		but_Skeleton.setBackground(new Color(163,76,0));
		but_Skeleton.setBorder(BorderFactory.createBevelBorder(ABORT));
		
		this.setTitle("Menu de changement des automates :"); // sets title of this frame.
		this.setDefaultCloseOperation(HIDE_ON_CLOSE); // Gère le bouton croix de la fenetre.
		this.setResizable(false); //Can't resized the frame.
		this.setSize(600, 600); //Set the size of the frame.
		this.setVisible(true); // make it visible.
		
		/*ImageIcon image = new ImageIcon("lien de la ressource"); //set the logo of the frame.
		this.setIconImage(image.getImage());
		*/
		this.getContentPane().setBackground(new Color(163,76,0));
		this.add(but_J1);
		this.add(but_J2);
		this.add(but_Bloon);
		this.add(but_Bat);
		this.add(but_Skeleton);
		this.add(label);
	}

	@Override
	public void actionPerformed (ActionEvent e) {
		if (e.getSource()==but_J1) {
			System.out.println("BUTTON !");
			//label.setVisible(true); //Affiche le label si button activer.
			
			JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("resources/Automata"));
			int response = (fileChooser.showOpenDialog(null)); //Select file to open.
			
			if(response == JFileChooser.APPROVE_OPTION) {
				File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				System.out.println(file);
			}
		}
		
		// Le ratioBouton peut être interressant. Un groupe de boutton ou un seul peut être séléctionné. Genre les test moodle un peu.
		// Je peux auusi faire une menuBar, comme dans eclipse, mais ça peut me prendre plus de temps.
		// Je peux faire une interface qui permet l'aller chercher dans les dossiers et fichier.
		
	}

}
