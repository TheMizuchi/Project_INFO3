package model.map.generator;

import edu.polytech.oop.collections.ArrayList;
import edu.polytech.oop.collections.IList;


public class Node {

	Room content;
	int mid_x;
	int mid_y;
	Node dijk_proch;
	double dijk_pds;
	IList ListArc;


	Node (Room r) {
		content = r;
		mid_x = r.getUpperLeftX() + (r.getWidth() / 2);
		mid_y = r.getUpperLeftY() + (r.getHeight() / 2);
		ListArc = new ArrayList();
	}

	Node (int x, int y) {
		content = null;
		mid_x = x;
		mid_y = y;
		ListArc = new ArrayList();
	}

	int numberArcs () {
		return ListArc.length();
	}

	Node copy () {
		Node n = new Node(mid_x, mid_y);
		n.content = this.content;
		n.dijk_pds = dijk_pds;
		n.dijk_proch = dijk_proch;

		return n;
	}

	void addArc (Arc a) {

		for (int i = 0; i < numberArcs(); i++) {
			Arc current = (Arc) ListArc.elementAt(i);

			if (current.dest1 == a.dest1 && current.dest2 == a.dest2 || current.dest1 == a.dest2 && current.dest2 == a.dest1) {
				return;
			}
		}
		ListArc.insertAt(numberArcs(), a);
	}

	double distance (Node n) {
		double x = Math.abs((double) n.mid_x - (double) mid_x);
		double y = Math.abs((double) n.mid_y - (double) mid_y);
		return Math.sqrt(x * x + y * y);
	}

}