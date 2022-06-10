package view;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Window;

import common.MyTimer;
import common.TimerListener;
import view.graphicEntity.CowboyView;


public class MyCanvas extends Component {

	private static final long serialVersionUID = 1L;
	Window win;

	CowboyView m_cowboy;


	public MyCanvas () {
		m_cowboy = new CowboyView(200, 200);
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
	}

}
