package model.map;

public enum TileType {

	WALL(0), VOID(1);


	private int id;


	TileType (final int id) {
		this.id = id;
	}

	public int getID () {
		return id;
	}

}
