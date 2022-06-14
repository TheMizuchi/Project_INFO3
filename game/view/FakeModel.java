package view;

import edu.polytech.oop.collections.LinkedList;
import view.graphicEntity.CowboyView;


public class FakeModel {
	
	public static final int CowboyID = 0;

	class Cowboy implements EntityInterface {

		int x, y;
		CowboyView cv;


		public Cowboy (int x, int y) {
			this.x = x;
			this.y = y;
			this.cv = new CowboyView(this);
			canvas.createEntityView(this.cv);
		}

		@Override
		public int getPosX () {
			return this.x;
		}

		@Override
		public int getPosY () {
			return this.y;
		}

		@Override
		public int getOrientation () {
			// TODO Auto-generated method stub
			return 1;
		}
	}

	class Map {

		int carte[][];


		public Map () {
			//			int carte[][] = new int[5][5];
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
			carte = new int[11][11];
			carte[5][5] = 1;
		}

		public int[][] getMap () {
			return this.carte;
		}
	}

	class Torche {

		int x, y;


		public Torche (int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getPosX () {
			return this.x;
		}

		public int getPosY () {
			return this.y;
		}
	}


	LinkedList entity;
	Map map;
	MyCanvas canvas;
	Torche torch;
	double time;


	public FakeModel (MyCanvas canvas) {
		this.canvas = canvas;
		this.map = new Map();
		this.entity = new LinkedList();
		this.torch = new Torche(12 + 25 * 5, 12 + 25 * 5);

		for (int i = 0; i < 121; i++) {
			Cowboy c = new Cowboy(12 + 25 * (i % 11), 12 + 25 * ((int) (i / 11)));
			entity.insertAt(0, c);
		}
	}

	public void update () {
		this.time += 0.001;
		canvas.vp.setPosition(12 + 25 * 5 + 10 * Math.sin(time*10), 12 + 25 * 5 + 10 * Math.cos(time*10), 1 + time);
	}

}
