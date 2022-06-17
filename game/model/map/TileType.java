package model.map;

import model.Model;


public enum TileType {

	WALL(0, 0, -1), FLOOR(1, 1, -1), COWBOY(100, 1, Model.COWBOY_ID), J1(100 + Model.J1_ID, 1, Model.J1_ID),
	J2(100 + Model.J2_ID, 1, Model.J2_ID), SKELETON(100 + Model.SKELETON_ID, 1, Model.SKELETON_ID),
	BLOON(100 + Model.BLOON_ID, 1, Model.BLOON_ID), DART_MONKEY(100 + Model.DART_MONKEY_ID, 1, Model.DART_MONKEY_ID),
	BAT(100 + Model.BAT_ID, 1, Model.BAT_ID), TORCH(100 + Model.TORCH_ID, 1, Model.TORCH_ID);


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
