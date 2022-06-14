package model.map.generator;

import edu.polytech.oop.collections.ArrayList;
import edu.polytech.oop.collections.IList;


public class Node {

	Room content;
	int mid_x;
	int mid_y;
	int nba;
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
		return nba;
	}

	void addArc (Arc a) {

		for (int i = 0; i < nba; i++) {
			Arc current = (Arc) ListArc.elementAt(i);

			if (current.dest1 == a.dest1 && current.dest2 == a.dest2) {
				return;
			}
		}
		ListArc.insertAt(nba, a);
		nba++;
	}

	double distance (Node n) {
		return Math.sqrt(Math.abs((double) n.mid_x - (double) mid_x) + Math.abs((double) n.mid_y - (double) mid_y));
	}

}