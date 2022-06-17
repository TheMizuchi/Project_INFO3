package model.map;

import model.Model;


public enum TileType {

	WALL(0, 0, -1), FLOOR(1, 1, -1), COWBOY(100, 1, Model.COWBOY_ID), J1(101, 1, Model.J1_ID), J2(102, 1, Model.J2_ID),
	BLOON(103, 1, Model.BLOON_ID), ZOMBIE(104, 1, Model.ZOMBIE_ID), BAT(105, 1, Model.BAT_ID),
	DART_MONKEY(105, 1, Model.DART_MONKEY_ID);


	private int id;
	private int textureID;
	private int spawnerID;


	TileType (final int id, final int tid, final int sid) {
		this.id = id;
		this.textureID = tid;
		this.spawnerID = sid;
	}

	public int getID () {
		return id;
	}

	public int getTextureID () {
		return textureID;
	}

	public int getSpawnerID () {
		return spawnerID;
	}

}
