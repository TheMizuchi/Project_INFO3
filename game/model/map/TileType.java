package model.map;

public enum TileType {

	WALL(0), FLOOR(1), VOID(2);


	private int id;


	TileType (final int id) {
		this.id = id;
	}

	public int getID () {
		return id;
	}

}
