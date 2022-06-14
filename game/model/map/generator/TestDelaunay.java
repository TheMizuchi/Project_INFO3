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

		Triangle tri = new Triangle(A, B, C);

		if (tri.centerX != 2.5 && tri.centerY != 2.5) {
			throw new Exception("Bad center of the triangle");
		}

		/*
		 * if (tri.radius < 1.59) { throw new Exception("Bad center of the triangle"); }
		 */

		System.out.println("Test Circumscribed Circle OK.");
		System.out.println("Testing Contains Point");

		LinkedList nodes = new LinkedList();
		nodes.insertAt(0, A);
		nodes.insertAt(0, B);
		nodes.insertAt(0, C);
		nodes.insertAt(0, D);
		nodes.insertAt(0, E);
		nodes.insertAt(0, F);
		nodes.insertAt(0, G);

		Graph g = new Graph(nodes, 7);

		g.delaunay();

		for (int i = 0; i < 7; i++) {
			Node n = (Node) nodes.elementAt(i);
			System.out.println("From node (" + n.mid_x + ", " + n.mid_y + ")");

			for (int j = 0; j < n.numberArcs(); j++) {
				Arc a = (Arc) n.ListArc.elementAt(j);
				System.out.println("( " + a.dest1.mid_x + ", " + a.dest1.mid_y + ") to (" + a.dest2.mid_x + ", " + a.dest2.mid_y + ")");
			}
		}
	}
}
