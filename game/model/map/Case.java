package model.map;

public class Case {

	static final int EMPTY = 0;
	static final int WALL = 1;
	static final int ROOF = 2;

	int type;


	public Case () {
		type = 1;
	}

	public Case (int t) {
		type = t;
	}

}
