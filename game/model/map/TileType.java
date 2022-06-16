package model.map;

public enum TileType {

	VOID(0), FLOOR(1), WALL(2);


	private int id;


	TileType (final int id) {
		this.id = id;
	}

	public int getID () {
		return id;
	}

}
