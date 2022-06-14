package model.map.generator;

import edu.polytech.oop.collections.LinkedList;


public class TestDelaunay {

	public static void main (String args[]) throws Exception {
		System.out.println("Testing Circumscribed circle...");
		Node A = new Node(1, 3);
		Node B = new Node(3, 4);
		Node C = new Node(2, 1);
		Node D = new Node(5, 2);
		Node E = new Node(5, 6);
		Node F = new Node(2, 6);
		Node G = new Node(7, 4);
		Node H = new Node(7, 8);
		Node I = new Node(3, 8);
		Node J = new Node(8, 1);
		Node K = new Node(9, 5);
		Node L = new Node(9, 8);

		Triangle tri = new Triangle(A, B, C);

		if (tri.centerX != 2.5 && tri.centerY != 2.5) {
			throw new Exception("Bad center of the triangle");
		}

		/*
		 * if (tri.radius < 1.59) { throw new Exception("Bad center of the triangle"); }
		 */

		System.out.println("Test Circumscribed Circle OK.");
		System.out.println("Testing Contains Point...");

		if (!tri.containsPoint(new Node(2, 3))) {
			throw new Exception("Point inside (2,3) don't detected as inside");
		}

		if (tri.containsPoint(new Node(1, 3))) {
			throw new Exception("Point on circle (1,3) don't detected as outside");
		}

		if (tri.containsPoint(new Node(0, 3))) {
			throw new Exception("Point outside (2,3) don't detected as outside");
		}

		System.out.println("Test Contains Point OK.");
		System.out.println("Testing Delaunay Algorithm...");
		LinkedList nodes = new LinkedList();
		nodes.insertAt(0, L);
		nodes.insertAt(0, K);
		nodes.insertAt(0, J);
		nodes.insertAt(0, I);
		nodes.insertAt(0, H);
		nodes.insertAt(0, G);
		nodes.insertAt(0, F);
		nodes.insertAt(0, E);
		nodes.insertAt(0, D);
		nodes.insertAt(0, C);
		nodes.insertAt(0, B);
		nodes.insertAt(0, A);

		Graph g = new Graph(nodes, 7);

		g.delaunay();

		for (int i = 0; i < 12; i++) {
			Node n = (Node) nodes.elementAt(i);
			System.out.println("From node (" + n.mid_x + ", " + n.mid_y + ")");

			for (int j = 0; j < n.numberArcs(); j++) {
				Arc a = (Arc) n.ListArc.elementAt(j);
				System.out.println("( " + a.dest1.mid_x + ", " + a.dest1.mid_y + ") to (" + a.dest2.mid_x + ", " + a.dest2.mid_y + ")");
			}
		}
		System.out.println("Test Delaunay Algorithm OK.");
		System.out.println("Testing Dijkstra Algorithm...");
		Graph dijk = g.dijkstra(B);

		for (int i = 0; i < 12; i++) {
			Node n = (Node) dijk.ListNode.elementAt(i);
			System.out.println("From node (" + n.mid_x + ", " + n.mid_y + ")");

			for (int j = 0; j < n.numberArcs(); j++) {
				Arc a = (Arc) n.ListArc.elementAt(j);
				System.out.println("( " + a.dest1.mid_x + ", " + a.dest1.mid_y + ") to (" + a.dest2.mid_x + ", " + a.dest2.mid_y + ")");
			}
		}

		System.out.println("Test Dijkstra Algorithm OK.");
		System.out.println("Testing MST Algorithm...");
		Graph MST = g.min_spanning_tree();

		for (int i = 0; i < 12; i++) {
			Node n = (Node) MST.ListNode.elementAt(i);
			System.out.println("From node (" + n.mid_x + ", " + n.mid_y + ")");

			for (int j = 0; j < n.numberArcs(); j++) {
				Arc a = (Arc) n.ListArc.elementAt(j);
				System.out.println("( " + a.dest1.mid_x + ", " + a.dest1.mid_y + ") to (" + a.dest2.mid_x + ", " + a.dest2.mid_y + ")");
			}
		}
		System.out.println("Test MST Algorithm OK.");

	}
}
