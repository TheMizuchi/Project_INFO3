package model.map.generator;

import model.map.Case;


public class Room {

	private int upperLeftX, upperLeftY;
	private int width, height;

	private Case comp[][];
	private RoomType type;


	public Room (int w, int h, Case[][] composition, int typeID) {
		upperLeftX = -1; //On remplie à -1 parce que l'emplacement dès salle sera fait plus tard
		upperLeftY = -1;
		width = w;
		height = h;

		comp = new Case[w][h];

		type = null;
		RoomType[] roomTypes = RoomType.values();
		int k = 0;

		while (k < roomTypes.length && type == null) {
			if (roomTypes[k].getID() == typeID)
				type = roomTypes[k];
			k++;
		}

		for (int i = 0; i < w; i++) {

			for (int j = 0; j < h; j++) {
				comp[i][j] = composition[i][j];
			}
		}

	}

	public int getUpperLeftX () {
		return upperLeftX;
	}

	public int getUpperLeftY () {
		return upperLeftY;
	}

	public int getWidth () {
		return width;
	}

	public int getHeight () {
		return height;
	}

	public RoomType getType () {
		return type;
	}

	public Case[][] getComp () {
		return comp;
	}

	public void setUpperLeft (int x, int y) {
		this.upperLeftX = x;
		this.upperLeftY = y;
	}

}
