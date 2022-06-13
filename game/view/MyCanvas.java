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
	MapView m_map;


	public MyCanvas (int win_w, int win_h) {
		this.scale = 2;
		this.win_h = win_h;
		this.win_w = win_h;

		run();

	}

	void run () {
		m_cowboy = new CowboyView(100, 100, this.scale);
		m_cowboy.turnLeft();
		m_cowboy.setDelay(500);
		m_torche = new TorcheView(100, 100, 100, this.win_w, this.win_h, this.scale);

		//		int carte[][] = new int[5][5];
		//		carte[0][0] = 0;
		//		carte[0][1] = 0;
		//		carte[0][2] = 0;
		//		carte[0][3] = 0;
		//		carte[0][4] = 0;
		//		
		//		carte[1][0] = 0;
		//		carte[1][1] = 2;
		//		carte[1][2] = 1;
		//		carte[1][3] = 2;
		//		carte[1][4] = 0;
		//		
		//		carte[2][0] = 0;
		//		carte[2][1] = 2;
		//		carte[2][2] = 1;
		//		carte[2][3] = 2;
		//		carte[2][4] = 0;
		//		
		//		carte[3][0] = 0;
		//		carte[3][1] = 2;
		//		carte[3][2] = 1;
		//		carte[3][3] = 2;
		//		carte[3][4] = 0;
		//		
		//		carte[4][0] = 0;
		//		carte[4][1] = 0;
		//		carte[4][2] = 0;
		//		carte[4][3] = 0;
		//		carte[4][4] = 0;
		int carte[][] = new int[250][250];

		m_map = new MapView(carte);
		int res = m_map.hauteur();
		System.out.println("hauteur" + res);
		res = m_map.largeur();
		System.out.println("largeur" + res);
		//m_cowboy = new CowboyView(200, 200);
		m_cowboy.spin();
	}

	@Override
	public void paint (Graphics g) {
		m_cowboy.paint(g);
		m_map.paint(g);
		m_cowboy.paint(g);
		m_torche.paint(g);

	}

}
