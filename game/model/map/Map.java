package model.map;

public class Map {

	Case[][] grid;
	
	int nb_rooms;
	int lvl;
	
	int width;
	int height;


	public Map (int x, int y, int level) {
		width = x;
		height = y;
		lvl = level;
		nb_rooms = lvl * 4;
		
		grid = new Case[x][y];

		for (int i = 0; i < x; i++) {

			for (int j = 0; j < y; j++) {
				grid[i][j] = new Case();
			}
		}
	}

}
