package model.map.generator;

public class Arc {

	Node dest1;
	Node dest2;


	Arc (Node n1, Node n2) {
		dest1 = n1;
		dest2 = n2;

		n1.addArc(this);
		n2.addArc(this);

	}

}
