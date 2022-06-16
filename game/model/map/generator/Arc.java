package model.map.generator;

public class Arc {

	Node dest1;
	Node dest2;
	boolean done;


	Arc (Node n1, Node n2) {
		dest1 = n1;
		dest2 = n2;
		done = false;
	}

	public Node first () {
		return dest1;
	}

	public Node second () {
		return dest2;
	}
}
