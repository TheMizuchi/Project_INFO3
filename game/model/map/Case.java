package model.map;

public class Case {

	static final int Empty = 0;
	static final int Wall = 1;
	static final int Roof = 2;

	int type;


	public Case () {
		type = 1;
	}

	public Case (int t) {
		type = t;
	}

}
