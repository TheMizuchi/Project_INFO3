package view;

import edu.polytech.oop.collections.LinkedList;
import view.graphicEntity.CowboyView;
import view.graphicEntity.LightSourceView;


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

	class Torche implements ILightSource{

		double x, y;
		double radius;
		LightSourceView sv;


		public Torche (int x, int y, double radius) {
			this.x = x;
			this.y = y;
			this.radius = radius;
			this.sv = new LightSourceView(this);
			canvas.createLightSourceView(sv);
		}

		@Override
		public double getPosX () {
			return this.x;
		}
		
		@Override
		public double getPosY () {
			return this.y;
		}

		@Override
		public double getRadius () {
			return this.radius;
		}
	}


	LinkedList entity;
	Map map;
	MyCanvas canvas;
	LinkedList torch;
	double time;


	public FakeModel (MyCanvas canvas) {
		this.canvas = canvas;
		this.map = new Map();
		this.entity = new LinkedList();
		this.torch = new LinkedList();

		for (int i = 0; i < 121; i++) {
			Cowboy c = new Cowboy((i % 11)-5 , ((int) (i / 11))-5);
			entity.insertAt(0, c);
		}
		
		for (int i = 0; i<1; i++) {
			for(int j = -1; j<2; j++) {
				this.torch.insertAt(0, new Torche(i, j, 40));
			}
		}
		
	}

	public void update (long elapsed) {
		this.time += (double)(elapsed)/4000;
		canvas.vp.setPosition(Math.sin(time * 10), Math.cos(time * 10), 1 + time);
	}

}
