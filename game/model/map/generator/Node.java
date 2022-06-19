package model.map.generator;

import edu.polytech.oop.collections.ArrayList;
import edu.polytech.oop.collections.ICollection.Iterator;
import edu.polytech.oop.collections.IList;


public class Node {

	Room content;
	int mid_x;
	int mid_y;
	Node dijk_proch;
	double dijk_pds;
	IList ListArc;


	public Node (Room r) {
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

	public Room content () {
		return content;
	}

	public int centerX () {
		return mid_x;
	}

	public int centerY () {
		return mid_y;
	}

	public IList getListArc () {
		return ListArc;
	}

	public int numberArcs () {
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

		Iterator iter = ListArc.iterator();

		for (int i = 0; iter.hasNext(); i++) {
			Arc current = (Arc) iter.next();

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