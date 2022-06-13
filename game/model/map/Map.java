package model.map;

import java.util.ArrayList;

import model.map.generator.Room;


public class Map {

	World world;
	int level;

	int width;
	int height;

	Case[][] grid;
	ArrayList<Room> rooms; //Salles présentes dans l'étage


	public Map (World world, int w, int h, int level) {
		this.world = world;
		this.width = w;
		this.height = h;
		this.level = level;

		grid = new Case[w][h];

		for (int i = 0; i < w; i++) {

			for (int j = 0; j < h; j++) {
				grid[i][j] = new Case();
			}
		}

		rooms = new ArrayList<Room>();

		placeRoomsRandomly(10);
	}

	private void placeRoomsRandomly (int N) {
		throw new IllegalStateException("NYI");
	}

}
