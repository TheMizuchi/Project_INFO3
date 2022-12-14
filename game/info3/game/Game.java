/*
 * Copyright (C) 2020 Pr. Olivier Gruber Educational software for a basic game
 * development
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 *
 * Created on: March, 2020 Author: Pr. Olivier Gruber
 */
package info3.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.RandomAccessFile;

import javax.swing.JFrame;
import javax.swing.JLabel;

import common.MyTimer;
import info3.game.graphics.GameCanvas;
import info3.game.sound.RandomFileInputStream;
import model.Model;
import model.entity.J1;
import model.entity.J2;
import view.GameOver;
import view.MenuFrame;
import view.MyCanvas;
import view.Victory;


public class Game {

	static Game game;


	public static void main (String args[]) throws Exception {

		new MenuFrame();

		while (MenuFrame.getBoolFen() == true) {}

		try {
			System.out.println("Game starting...");
			game = new Game();
			System.out.println("Game started.");
		}
		catch (Throwable th) {
			th.printStackTrace(System.err);
		}
	}


	long m_elapsedUpdate;
	JFrame m_frame;
	JLabel m_text;
	GameCanvas m_canvas;
	MyCanvas my_canvas;
	CanvasListener m_listener;
	//Cowboy m_cowboy;
	Sound m_music;
	Model m_m;


	Game () throws Exception {
		// creating a cowboy, that would be a model
		// in an Model-View-Controller pattern (MVC)
		//m_cowboy = new Cowboy();
		// creating a listener for all the events
		// from the game canvas, that would be
		// the controller in the MVC pattern
		m_listener = new CanvasListener(this);
		Dimension d = new Dimension(1920, 980);//
		my_canvas = MyCanvas.getInstance();//
		my_canvas.initCanvas(d.width, d.height);//
		// creating the game canvas to render the game,
		// that would be a part of the view in the MVC pattern
		m_canvas = new GameCanvas(m_listener);

		new MyTimer();
		m_elapsedUpdate = 0;
		m_m = Model.getInstance();

		m_m.newLevel();

		m_listener.m_cont.setModel();
		my_canvas.initATH(null, null);//Model.getj1(), Model.getj2()); // ?? remplacer quand les m??thodes seront cr????es
		System.out.println("  - creating frame...");
		m_frame = m_canvas.createFrame(d);
		System.out.println("  - setting up the frame...");
		setupFrame();

	}

	/*
	 * Then it lays out the frame, with a border layout, adding a label to the north
	 * and the game canvas to the center.
	 */
	private void setupFrame () {

		m_frame.setTitle("Game");
		m_frame.setLayout(new BorderLayout());

		m_frame.add(m_canvas, BorderLayout.CENTER);

		m_text = new JLabel();
		m_text.setText("Tick: 0ms FPS=0");
		m_frame.add(m_text, BorderLayout.NORTH);

		// center the window on the screen
		m_frame.setLocationRelativeTo(null);

		// make the vindow visible
		m_frame.setVisible(true);
	}

	/*
	 * ================================================================ All the
	 * methods below are invoked from the GameCanvas listener, once the window is
	 * visible on the screen.
	 * ==============================================================
	 */


	/*
	 * Called from the GameCanvas listener when the frame
	 */
	String m_musicName;


	void loadMusic () {
		m_musicName = m_musicNames[m_musicIndex];
		String filename = "resources/" + m_musicName + ".ogg";
		m_musicIndex = (m_musicIndex + 1) % m_musicNames.length;

		try {
			RandomAccessFile file = new RandomAccessFile(filename, "r");
			RandomFileInputStream fis = new RandomFileInputStream(file);
			m_canvas.playMusic(fis, 0, 1.0F);
		}
		catch (Throwable th) {
			th.printStackTrace(System.err);
			System.exit(-1);
		}
	}


	private int m_musicIndex = 0;
	private String[] m_musicNames = new String[] { "DB_Theme" };

	private long m_textElapsed;

	/*
	 * This method is invoked almost periodically, given the number of milli-seconds
	 * that elapsed since the last time this method was invoked.
	 */
	long elapsedDeath = 0;


	void tick (long elapsed) {

		//m_cowboy.tick(elapsed);

		// Update every second
		// the text on top of the frame: tick and fps
		m_textElapsed += elapsed;
		m_elapsedUpdate += elapsed;

		if (m_textElapsed > 1000) {
			m_textElapsed = 0;
			float period = m_canvas.getTickPeriod();
			int fps = m_canvas.getFPS();

			String txt = "Tick=" + period + "ms";
			while (txt.length() < 15)
				txt += " ";
			txt = txt + fps + " fps   ";
			m_text.setText(txt);
		}

		if (m_elapsedUpdate >= 20) {
			m_m.update(m_elapsedUpdate);
			m_elapsedUpdate -= 20;
		}

		if (J1.getInstance().isDeath() || J2.getInstance().isDeath()) {
			GameOver.getInstance();
			this.elapsedDeath += elapsed;
			if (this.elapsedDeath > 3000)
				System.exit(0);
		} else if (Model.getInstance().getLevel() >= 4) {
			Victory.getInstance();
			this.elapsedDeath += elapsed;
			if (this.elapsedDeath > 3000)
				System.exit(0);
		}

	}

	/*
	 * This request is to paint the Game Canvas, using the given graphics. This is
	 * called from the GameCanvasListener, called from the GameCanvas.
	 */
	void paint (Graphics g) {

		// get the size of the canvas
		int width = m_canvas.getWidth();
		int height = m_canvas.getHeight();

		// erase background
		g.setColor(Color.gray);
		g.fillRect(0, 0, width, height);

		// paint
		//m_cowboy.paint(g, width, height);
		my_canvas.paint(g);

	}

}