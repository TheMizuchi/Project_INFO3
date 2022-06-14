package model.map.generator;

public enum RoomType {

	SPAWN(0), BOSS1(1), BOSS2(2), BOSS3(3), KEY(4), FIGHT(5), ENIGMA(5);


	private int id;


	RoomType (final int id) {
		this.id = id;
	}

	public int getID () {
		return id;
	}

}
