package model.map;

public enum TileType {

	WALL(0), FLOOR(1), SOMETHING(2), SOMETHING_ELSE(3), VOID(4);


	private int id;


	TileType (final int id) {
		this.id = id;
	}

	public int getID () {
		return id;
	}

}
