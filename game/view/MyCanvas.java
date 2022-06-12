package view;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Window;
import java.io.IOException;

import common.MyTimer;
import common.TimerListener;
import view.graphicEntity.CowboyView;


public class MyCanvas extends Component {

	private static final long serialVersionUID = 1L;
	Window win;

	CowboyView m_cowboy;
	MapView m_map;


	public MyCanvas () {
		int carte[][] = new int[5][5];
		carte[0][0] = 0;
		carte[0][1] = 0;
		carte[0][2] = 0;
		carte[0][3] = 0;
		carte[0][4] = 0;
		
		carte[1][0] = 0;
		carte[1][1] = 2;
		carte[1][2] = 1;
		carte[1][3] = 2;
		carte[1][4] = 0;
		
		carte[2][0] = 0;
		carte[2][1] = 2;
		carte[2][2] = 1;
		carte[2][3] = 2;
		carte[2][4] = 0;
		
		carte[3][0] = 0;
		carte[3][1] = 2;
		carte[3][2] = 1;
		carte[3][3] = 2;
		carte[3][4] = 0;
		
		carte[4][0] = 0;
		carte[4][1] = 0;
		carte[4][2] = 0;
		carte[4][3] = 0;
		carte[4][4] = 0;
		//int carte[][] = new int [10][10];

		m_map = new MapView(carte);
		int res = m_map.hauteur();
		System.out.println("hauteur" + res);
		res = m_map.largeur();
		System.out.println("largeur" + res);

		m_cowboy = new CowboyView(200, 200);
		m_cowboy.spin();
	}

	//	class Refresher implements TimerListener {
	//
	//		MyTimer m_t;
	//
	//
	//		public Refresher () {
	//			m_t = MyTimer.getTimer();
	//			m_t.setTimer(0, this);
	//		}
	//
	//		@Override
	//		public void expired () {
	//			repaint();
	//			m_t.setTimer(1000 / 30, this);
	//		}
	//	}

	@Override
	public void paint (Graphics g) {

		m_cowboy.paint(g);
		m_map.paint(g);
	}

}
