package view;

import java.awt.Component;
import java.awt.Graphics;
import view.graphicEntity.CowboyView;


public class MyCanvas extends Component {

	private static final long serialVersionUID = 1L;
	//Window win;
	int win_h;
	int win_w;
	double scale;
	CowboyView m_cowboy;
	TorcheView m_torche;


	public MyCanvas (int win_w, int win_h) {
		this.scale = 2;
		this.win_h = win_h;
		this.win_w = win_h;
		
		run();
		
	}
	
	void run() {
		m_cowboy = new CowboyView(100, 100, this.scale);
		m_cowboy.turnLeft();
		m_cowboy.setDelay(500);
		m_torche = new TorcheView(100, 100, 100, this.win_w, this.win_h, this.scale);
	}


	@Override
	public void paint (Graphics g) {
		m_cowboy.paint(g);
		m_torche.paint(g);
	}

}
