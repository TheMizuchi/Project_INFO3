package model.map.generator;

import model.map.Map;


public class Generator {

	Map mapCurrent;
	int level;
	Room rooms[];
	int nb_rooms;


	public Generator (Map m, int level) {
		mapCurrent = m;
		this.level = level;

	}
}
