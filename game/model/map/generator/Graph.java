package model.map.generator;

import edu.polytech.oop.collections.ArrayList;
import edu.polytech.oop.collections.IList;


public class Graph {

	IList ListNode;
	int nbn;


	private class Node {

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
			ListArc.insertAt(0, a);
			nba++;
		}

	}

	private class Arc {

		Room dest1;
		Room dest2;


		Arc (Node n1, Node n2) {
			dest1 = n1.content;
			dest2 = n2.content;

			n1.addArc(this);
			n2.addArc(this);

		}

	}

	private class Triangle {

		Node A;
		Node B;
		Node C;
		Arc AB;
		Arc AC;
		Arc BC;
		boolean done;


		Triangle (Node a, Node b, Node c) {
			A = a;
			B = b;
			C = c;
			AB = new Arc(A, B);
			AC = new Arc(A, C);
			BC = new Arc(B, C);
			done = false;
		}

		boolean containsPoints (IList nodes) {
			//TODO
			return false;
		}
	}


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

	void delaunay () {

		Triangle supertriangle = supertri(getPosXMax(), getPosYMax(), 1);

		IList triangles = new ArrayList();
		triangles.insertAt(0, supertriangle);

		for (int i = 0; i < nbn; i++) {

			IList englobant = new ArrayList();
			IList suppress = new ArrayList();

			for (int t = 0; t < triangles.length(); i++) {

			}
		}

	}

}
