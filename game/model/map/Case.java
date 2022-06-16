package model.map;

public class Case {

	private TileType type;


	public Case () {
		type = TileType.WALL;
	}

	public Case (int typeID) {
		type = null;
		TileType[] tileTypes = TileType.values();
		int k = 0;

		while (k < tileTypes.length && type == null) {
			if (tileTypes[k].getID() == typeID)
				type = tileTypes[k];
			k++;
		}
	}

	public TileType getType () {
		return type;
	}
	
	public void setType(TileType t) {
		this.type = t;
	}

}
