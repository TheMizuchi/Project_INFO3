package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class MenuFrame extends JFrame implements ActionListener {

	static boolean fen = true;

	JButton but_J1;
	JButton but_J2;
	JButton but_Bloon;
	JButton but_Bat;
	JButton but_Skeleton;
	JButton but_Archer;
	JButton but_Doge;
	JButton but_Mystery;
	JButton but_launch;
	JButton but_BigBloon;

	JFileChooser file_J1;
	JFileChooser file_J2;
	JFileChooser file_Bloon;
	JFileChooser file_Bat;
	JFileChooser file_Skeleton;
	JFileChooser file_Archer;
	JFileChooser file_Doge;
	JFileChooser file_Mystery;
	JFileChooser file_BigBloon;

	static String fileJ1 = "resources/Automata/Default/J1.gal";
	static String fileJ2 = "resources/Automata/Default/J2.gal";
	static String fileBloon = "resources/Automata/Default/Bloon.gal";
	static String fileBat = "resources/Automata/Default/Bat.gal";
	static String fileSkeleton = "resources/Automata/Default/Skeleton.gal";
	static String fileArcher = "resources/Automata/Default/Archer.gal";
	static String fileDoge = "resources/Automata/Default/Doge.gal";
	static String fileMystery = "resources/Automata/Default/Mystery.gal";
	static String fileBigBloon = "resources/Automata/Default/Bloon.gal";

	JLabel label;


	public MenuFrame () {

		label = new JLabel();

		but_J1 = new JButton();
		but_J2 = new JButton();
		but_Bloon = new JButton();
		but_Bat = new JButton();
		but_Skeleton = new JButton();
		but_Archer = new JButton();
		but_Doge = new JButton();
		but_Mystery = new JButton();
		but_BigBloon = new JButton();
		but_launch = new JButton();

		but_J1.setBounds(200, 0, 200, 30);
		but_J2.setBounds(200, 30, 200, 30);
		but_Bloon.setBounds(200, 60, 200, 30);
		but_Bat.setBounds(200, 90, 200, 30);
		but_Skeleton.setBounds(200, 120, 200, 30);
		but_Archer.setBounds(200, 150, 200, 30);
		but_Doge.setBounds(200, 180, 200, 30);
		but_Mystery.setBounds(200, 210, 200, 30);
		but_BigBloon.setBounds(200, 240, 200, 30);
		but_launch.setBounds(200, 270, 200, 30);

		but_J1.addActionListener(this);
		//button.addActionListener(e -> System.out.println("BOUTON !"));
		but_J2.addActionListener(this);
		but_Bloon.addActionListener(this);
		but_Bat.addActionListener(this);
		but_Skeleton.addActionListener(this);
		but_Archer.addActionListener(this);
		but_Doge.addActionListener(this);
		but_Mystery.addActionListener(this);
		but_BigBloon.addActionListener(this);
		but_launch.addActionListener(this);

		but_J1.setText("Le J1");
		but_J2.setText("Le J2");
		but_Bloon.setText("Le Ballon");
		but_Bat.setText("La chauve-souris");
		but_Skeleton.setText("Le squelette");
		but_Archer.setText("L'archer");
		but_Doge.setText("Le Doge");
		but_Mystery.setText("La Mystery Machine");
		but_BigBloon.setText("Le Big Ballon");
		but_launch.setText("Launch");

		but_J1.setFocusable(false);
		but_J2.setFocusable(false);
		but_Bloon.setFocusable(false);
		but_Bat.setFocusable(false);
		but_Skeleton.setFocusable(false);
		but_Archer.setFocusable(false);
		but_Doge.setFocusable(false);
		but_Mystery.setFocusable(false);
		but_BigBloon.setFocusable(false);
		but_launch.setFocusable(false);

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
		but_Archer.setHorizontalTextPosition(JButton.CENTER);
		but_Archer.setVerticalTextPosition(JButton.CENTER);
		but_Doge.setHorizontalTextPosition(JButton.CENTER);
		but_Doge.setVerticalTextPosition(JButton.CENTER);
		but_Mystery.setHorizontalTextPosition(JButton.CENTER);
		but_Mystery.setVerticalTextPosition(JButton.CENTER);
		but_BigBloon.setHorizontalTextPosition(JButton.CENTER);
		but_BigBloon.setVerticalTextPosition(JButton.CENTER);
		but_launch.setHorizontalTextPosition(JButton.CENTER);
		but_launch.setVerticalTextPosition(JButton.CENTER);

		but_J1.setFont(new Font("Comic sans", Font.BOLD, 13));
		but_J1.setForeground(Color.BLACK);
		but_J1.setBackground(new Color(163, 76, 0));
		but_J1.setBorder(BorderFactory.createBevelBorder(ABORT)); //Permet de custom les bords du button.
		//button.setEnabled(false); Desactive le button. Si on le met dans le if, alors on peut le cliquer une seul fois.
		but_J2.setFont(new Font("Comic sans", Font.BOLD, 13));
		but_J2.setForeground(Color.BLACK);
		but_J2.setBackground(new Color(163, 76, 0));
		but_J2.setBorder(BorderFactory.createBevelBorder(ABORT));

		but_Bloon.setFont(new Font("Comic sans", Font.BOLD, 13));
		but_Bloon.setForeground(Color.BLACK);
		but_Bloon.setBackground(new Color(163, 76, 0));
		but_Bloon.setBorder(BorderFactory.createBevelBorder(ABORT));

		but_Bat.setFont(new Font("Comic sans", Font.BOLD, 13));
		but_Bat.setForeground(Color.BLACK);
		but_Bat.setBackground(new Color(163, 76, 0));
		but_Bat.setBorder(BorderFactory.createBevelBorder(ABORT));

		but_Skeleton.setFont(new Font("Comic sans", Font.BOLD, 13));
		but_Skeleton.setForeground(Color.BLACK);
		but_Skeleton.setBackground(new Color(163, 76, 0));
		but_Skeleton.setBorder(BorderFactory.createBevelBorder(ABORT));

		but_Archer.setFont(new Font("Comic sans", Font.BOLD, 13));
		but_Archer.setForeground(Color.BLACK);
		but_Archer.setBackground(new Color(163, 76, 0));
		but_Archer.setBorder(BorderFactory.createBevelBorder(ABORT));

		but_Doge.setFont(new Font("Comic sans", Font.BOLD, 13));
		but_Doge.setForeground(Color.BLACK);
		but_Doge.setBackground(new Color(163, 76, 0));
		but_Doge.setBorder(BorderFactory.createBevelBorder(ABORT));

		but_Mystery.setFont(new Font("Comic sans", Font.BOLD, 13));
		but_Mystery.setForeground(Color.BLACK);
		but_Mystery.setBackground(new Color(163, 76, 0));
		but_Mystery.setBorder(BorderFactory.createBevelBorder(ABORT));

		but_BigBloon.setFont(new Font("Comic sans", Font.BOLD, 13));
		but_BigBloon.setForeground(Color.BLACK);
		but_BigBloon.setBackground(new Color(163, 76, 0));
		but_BigBloon.setBorder(BorderFactory.createBevelBorder(ABORT));

		but_launch.setFont(new Font("Comic sans", Font.BOLD, 13));
		but_launch.setForeground(Color.BLACK);
		but_launch.setBackground(Color.RED);
		but_launch.setBorder(BorderFactory.createBevelBorder(ABORT));

		this.setTitle("Menu de changement des automates :"); // sets title of this frame.
		this.setDefaultCloseOperation(HIDE_ON_CLOSE); // Gère le bouton croix de la fenetre.
		this.setResizable(false); //Can't resized the frame.
		this.setSize(600, 600); //Set the size of the frame.
		this.setVisible(true); // make it visible.

		/*
		 * ImageIcon image = new ImageIcon("lien de la ressource"); //set the logo of
		 * the frame. this.setIconImage(image.getImage());
		 */
		this.getContentPane().setBackground(new Color(163, 76, 0));
		this.add(but_J1);
		this.add(but_J2);
		this.add(but_Bloon);
		this.add(but_Bat);
		this.add(but_Skeleton);
		this.add(but_Archer);
		this.add(but_Doge);
		this.add(but_Mystery);
		this.add(but_BigBloon);
		this.add(but_launch);
		this.add(label);

		this.getDefaultCloseOperation();
	}

	@Override
	public void actionPerformed (ActionEvent e) {

		if (e.getSource() == but_J1) {
			//System.out.println("BUTTON !");
			//label.setVisible(true); //Affiche le label si button activer.

			file_J1 = new JFileChooser();
			file_J1.setCurrentDirectory(new File("resources/Automata"));
			int response = (file_J1.showOpenDialog(null)); //Select file to open.

			if (response == JFileChooser.APPROVE_OPTION) {
				fileJ1 = new String(file_J1.getSelectedFile().getAbsolutePath());
				System.out.println(fileJ1);
			}
		} else if (e.getSource() == but_J2) {
			file_J2 = new JFileChooser();
			file_J2.setCurrentDirectory(new File("resources/Automata"));
			int response = (file_J2.showOpenDialog(null)); //Select file to open.

			if (response == JFileChooser.APPROVE_OPTION) {
				fileJ2 = new String(file_J2.getSelectedFile().getAbsolutePath());
				System.out.println(fileJ2);
			}
		} else if (e.getSource() == but_Bloon) {
			file_Bloon = new JFileChooser();
			file_Bloon.setCurrentDirectory(new File("resources/Automata"));
			int response = (file_Bloon.showOpenDialog(null)); //Select file to open.

			if (response == JFileChooser.APPROVE_OPTION) {
				fileBloon = new String(file_Bloon.getSelectedFile().getAbsolutePath());
				System.out.println(fileBloon);
			}
		} else if (e.getSource() == but_Bat) {
			file_Bat = new JFileChooser();
			file_Bat.setCurrentDirectory(new File("resources/Automata"));
			int response = (file_Bat.showOpenDialog(null)); //Select file to open.

			if (response == JFileChooser.APPROVE_OPTION) {
				fileBat = new String(file_Bat.getSelectedFile().getAbsolutePath());
				System.out.println(fileBat);
			}
		} else if (e.getSource() == but_Skeleton) {
			file_Skeleton = new JFileChooser();
			file_Skeleton.setCurrentDirectory(new File("resources/Automata"));
			int response = (file_Skeleton.showOpenDialog(null)); //Select file to open.

			if (response == JFileChooser.APPROVE_OPTION) {
				fileSkeleton = new String(file_Skeleton.getSelectedFile().getAbsolutePath());
				System.out.println(fileSkeleton);
			}
		} else if (e.getSource() == but_Archer) {
			file_Archer = new JFileChooser();
			file_Archer.setCurrentDirectory(new File("resources/Automata"));
			int response = (file_Archer.showOpenDialog(null)); //Select file to open.

			if (response == JFileChooser.APPROVE_OPTION) {
				fileArcher = new String(file_Archer.getSelectedFile().getAbsolutePath());
				System.out.println(fileArcher);
			}
		} else if (e.getSource() == but_Doge) {
			file_Doge = new JFileChooser();
			file_Doge.setCurrentDirectory(new File("resources/Automata"));
			int response = (file_Doge.showOpenDialog(null)); //Select file to open.

			if (response == JFileChooser.APPROVE_OPTION) {
				fileDoge = new String(file_Doge.getSelectedFile().getAbsolutePath());
				System.out.println(fileDoge);
			}
		} else if (e.getSource() == but_Mystery) {
			file_Mystery = new JFileChooser();
			file_Mystery.setCurrentDirectory(new File("resources/Automata"));
			int response = (file_Mystery.showOpenDialog(null)); //Select file to open.

			if (response == JFileChooser.APPROVE_OPTION) {
				fileMystery = new String(file_Mystery.getSelectedFile().getAbsolutePath());
				System.out.println(fileMystery);
			}
		} else if (e.getSource() == but_BigBloon) {
			file_BigBloon = new JFileChooser();
			file_BigBloon.setCurrentDirectory(new File("resources/Automata"));
			int response = (file_BigBloon.showOpenDialog(null)); //Select file to open.

			if (response == JFileChooser.APPROVE_OPTION) {
				fileBigBloon = new String(file_BigBloon.getSelectedFile().getAbsolutePath());
				System.out.println(fileBigBloon);
			}
		} else if (e.getSource() == but_launch) {
			fen = false;
		}

		// Le ratioBouton peut être interressant. Un groupe de boutton ou un seul peut être séléctionné. Genre les test moodle un peu.
		// Je peux auusi faire une menuBar, comme dans eclipse, mais ça peut me prendre plus de temps.
		// Je peux faire une interface qui permet l'aller chercher dans les dossiers et fichier.

	}

	public static String getFileJ1 () {
		return fileJ1;
	}

	public static String getFileJ2 () {
		return fileJ2;
	}

	public static String getFileBloon () {
		return fileBloon;
	}

	public static String getFileBigBloon () {
		return fileBigBloon;
	}

	public static String getFileBat () {
		return fileBat;
	}

	public static String getFileSkeleton () {
		return fileSkeleton;
	}

	public static String getFileArcher () {
		return fileArcher;
	}

	public static String getFileDoge () {
		return fileDoge;
	}

	public static String getFileMystery () {
		return fileMystery;
	}

	public static Boolean getBoolFen () {
		return fen;
	}

}
