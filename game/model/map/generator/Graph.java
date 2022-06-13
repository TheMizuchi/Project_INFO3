package model.map.generator;

import edu.polytech.oop.collections.IList;
import edu.polytech.oop.collections.ArrayList;


public class Graph {

	IList ListNode;
	int nbn;


	Graph (IList rooms) {
		int l = rooms.length();
		ListNode = new ArrayList();
		Room r;

		for (int i = 0; i < l; i++) {
			r = (Room) rooms.removeAt(0);
			Node n = new Node(r);
			ListNode.insertAt(0, n);
		}
	}

	int getPosXMax () {
		int l = ListNode.length();
		int max = 0;
		Node n;

		for (int i = 0; i < l; i++) {
			n = (Node) ListNode.elementAt(i);

			if (max < n.mid_x) {
				max = n.mid_x;
			}
		}
		return max;
	}

	int getPosYMax () {
		int l = ListNode.length();
		int max = 0;
		Node n;

		for (int i = 0; i < l; i++) {
			n = (Node) ListNode.elementAt(i);

			if (max < n.mid_y) {
				max = n.mid_y;
			}
		}
		return max;
	}

	Triangle supertri (int max_x, int max_y, int espilon) {
		Node A = new Node(-espilon, -espilon);
		Node B = new Node((2 * max_x + 3 * espilon), -espilon);
		Node C = new Node(-espilon, (2 * max_y + 3 * espilon));

		return new Triangle(A, B, C);
	}

	void delaunay (IList nodes, int nbn) {

		Triangle supertriangle = supertri(getPosXMax(), getPosYMax(), 1);

		IList triangles = new ArrayList();
		triangles.insertAt(0, supertriangle);

		for (int i = 0; i < nbn; i++) {

			Node n = (Node) nodes.elementAt(i);

			IList englobant = new ArrayList();
			IList suppress = new ArrayList();

			Triangle tri;

			for (int t = 0; t < triangles.length(); t++) {
				tri = (Triangle) triangles.elementAt(i);

				if (tri.containsPoint(n)) {
					suppress.insertAt(0, tri);

					englobant.insertAt(0, new Arc(tri.A, tri.B));
					englobant.insertAt(0, new Arc(tri.A, tri.C));
					englobant.insertAt(0, new Arc(tri.C, tri.B));
				}
			}

			for (int ts = 0; ts < suppress.length(); ts++) {
				triangles.remove(suppress.elementAt(i));
			}

			for (int ar = 0; ar < englobant.length(); ar++) {
				Node Pt1 = ((Arc) englobant.elementAt(i)).dest1;
				Node Pt2 = ((Arc) englobant.elementAt(i)).dest2;

				Triangle newTri = new Triangle(Pt1, Pt2, n);
				triangles.insertAt(0, newTri);
			}
		}

	}

}
