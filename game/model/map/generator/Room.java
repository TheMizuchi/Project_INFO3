package model.map.generator;

import model.map.Case;


public class Room {

	int upperLeftX, upperLeftY;
	int width, height;

	Case comp[][];
	int type;


	Room (int w, int h, Case[][] composition, int t) {
		width = w;
		height = h;

		comp = new Case[w][h];
		type = t;

		for (int i = 0; i < w; i++) {

			for (int j = 0; j < h; j++) {
				comp[i][j] = composition[i][j];
			}
		}

	}

	public int getWidth () {
		return width;
	}

	public int getHeight () {
		return height;
	}

}
