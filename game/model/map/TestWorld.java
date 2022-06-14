package model.map;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.json.simple.parser.ParseException;


public class TestWorld {

	public static void main (String[] args) throws ParseException, IOException {
		World w = new World("resources/rooms.json");
		Map m = new Map(w, 160, 120, 1);
		new Frame(w, m);
	}

}


class Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	World w;
	Map m;
	Panel p;


	public Frame (World w, Map m) {
		this.w = w;
		this.m = m;
		p = new Panel(w, m);
		this.setContentPane(p);
		this.setSize(800, 600);
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}


class Panel extends JPanel {

	private static final long serialVersionUID = 1L;
	World w;
	Map m;


	public Panel (World w, Map m) {
		this.w = w;
		this.m = m;
	}

	public void paintComponent (Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		for (int i = 0; i < m.width; i++) {

			for (int j = 0; j < m.height; j++) {
				if (m.grid[i][j].type == TileType.WALL)
					g.setColor(Color.BLACK);
				else if (m.grid[i][j].type == TileType.FLOOR)
					g.setColor(Color.WHITE);
				g.fillRect(i * this.getWidth() / m.width, j * this.getHeight() / m.height, this.getWidth() / m.width, this.getHeight() / m.height);
			}
		}
		/*
		for (int i = 0; i < m.width; i++) {
			g.setColor(Color.WHITE);
			g.drawLine(0, i * this.getHeight() / m.height, this.getWidth(), i * this.getHeight() / m.height);
			g.drawLine(i * this.getWidth() / m.width, 0, i * this.getWidth() / m.width, this.getHeight());
		}
		*/
	}

}