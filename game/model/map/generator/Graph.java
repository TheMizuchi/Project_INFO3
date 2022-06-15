package model.map.generator;

import edu.polytech.oop.collections.ArrayList;
import edu.polytech.oop.collections.IList;


public class Graph {

	IList ListNode;


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

	Graph (IList nodes, int nbn) {
		this.ListNode = nodes;
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

	void delaunay () {

		Triangle supertriangle = supertri(getPosXMax(), getPosYMax(), 1);

		IList triangles = new ArrayList();
		triangles.insertAt(0, supertriangle);

		for (int i = 0; i < ListNode.length(); i++) {

			Node n = (Node) ListNode.elementAt(i);

			IList englobant = new ArrayList();
			IList suppress = new ArrayList();

			Triangle tri;

			for (int t = 0; t < triangles.length(); t++) {
				tri = (Triangle) triangles.elementAt(t);

				if (tri.containsPoint(n)) {
					suppress.insertAt(0, tri);
				}
			}

			for (int ts = 0; ts < suppress.length(); ts++) {
				tri = (Triangle) suppress.elementAt(ts);

				if (tri.edgeShared(tri.AB, suppress)) {
					englobant.insertAt(0, tri.AB);
				}

				if (tri.edgeShared(tri.AC, suppress)) {
					englobant.insertAt(0, tri.AC);
				}

				if (tri.edgeShared(tri.BC, suppress)) {
					englobant.insertAt(0, tri.BC);
				}
			}

			for (int ts = 0; ts < suppress.length(); ts++) {
				triangles.remove(suppress.elementAt(ts));
			}

			for (int ar = 0; ar < englobant.length(); ar++) {
				Node Pt1 = ((Arc) englobant.elementAt(ar)).dest1;
				Node Pt2 = ((Arc) englobant.elementAt(ar)).dest2;

				Triangle newTri = new Triangle(Pt1, Pt2, n);
				triangles.insertAt(0, newTri);
			}
		}
		ArrayList suppress = new ArrayList();

		for (int t = 0; t < triangles.length(); t++) {
			Triangle tri = (Triangle) triangles.elementAt(t);

			for (int i = 0; i < ListNode.length(); i++) {
				Node n = (Node) ListNode.elementAt(i);

				if (tri.containsPoint(n) && !suppress.contains(tri)) {
					suppress.insertAt(0, tri);
				}
			}
		}

		for (int s = 0; s < suppress.length(); s++) {
			triangles.remove(suppress.elementAt(s));
		}

		for (int t = 0; t < triangles.length(); t++) {
			Triangle tri = (Triangle) triangles.elementAt(t);

			if (!(tri.AB.dest1.equals(supertriangle.A) || tri.AB.dest2.equals(supertriangle.A) || tri.AB.dest1.equals(supertriangle.B) || tri.AB.dest2.equals(supertriangle.B) || tri.AB.dest1.equals(supertriangle.C) || tri.AB.dest2.equals(supertriangle.C))) {
				tri.AB.dest1.addArc(tri.AB);
				tri.AB.dest2.addArc(tri.AB);
			}

			if (!(tri.AC.dest1.equals(supertriangle.A) || tri.AC.dest2.equals(supertriangle.A) || tri.AC.dest1.equals(supertriangle.B) || tri.AC.dest2.equals(supertriangle.B) || tri.AC.dest1.equals(supertriangle.C) || tri.AC.dest2.equals(supertriangle.C))) {
				tri.AC.dest1.addArc(tri.AC);
				tri.AC.dest2.addArc(tri.AC);
			}

			if (!(tri.BC.dest1.equals(supertriangle.A) || tri.BC.dest2.equals(supertriangle.A) || tri.BC.dest1.equals(supertriangle.B) || tri.BC.dest2.equals(supertriangle.B) || tri.BC.dest1.equals(supertriangle.C) || tri.BC.dest2.equals(supertriangle.C))) {
				tri.BC.dest1.addArc(tri.BC);
				tri.BC.dest2.addArc(tri.BC);
			}
		}

	}

}
