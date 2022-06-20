package model.map.generator;

import java.util.Random;

import edu.polytech.oop.collections.ArrayList;
import edu.polytech.oop.collections.ICollection.Iterator;
import edu.polytech.oop.collections.IList;
import edu.polytech.oop.collections.LinkedList;


public class Graph {

	public IList ListNode;


	public Graph (IList rooms) {
		ListNode = new ArrayList();
		Room r;

		Iterator iterRoom = rooms.iterator();

		while (iterRoom.hasNext()) {

			r = (Room) iterRoom.next();

			if (r.getUpperLeftX() != -1 && r.getUpperLeftY() != -1) {
				Node n = new Node(r);
				ListNode.insertAt(0, n);
			}
		}
	}

	Graph (IList nodes, int nbn) {
		this.ListNode = nodes;
	}

	boolean containsArc (int d1x, int d1y, int d2x, int d2y) {

		for (int i = 0; i < ListNode.length(); i++) {
			Node n = (Node) ListNode.elementAt(i);

			for (int j = 0; j < n.ListArc.length(); j++) {
				Arc a = (Arc) n.ListArc.elementAt(j);

				if (a.dest1.mid_x == d1x && a.dest1.mid_y == d1y && a.dest2.mid_x == d2x && a.dest2.mid_y == d2y) {
					return true;
				}

				if (a.dest2.mid_x == d1x && a.dest2.mid_y == d1y && a.dest1.mid_x == d2x && a.dest1.mid_y == d2y) {
					return true;
				}
			}
		}
		return false;
	}

	Node findNode (int x, int y) {

		for (int i = 0; i < ListNode.length(); i++) {
			Node n = (Node) ListNode.elementAt(i);

			if (n.mid_x == x && n.mid_y == y) {
				return n;
			}
		}
		return null;
	}

	int numberArc () {
		int nba = 0;

		for (int i = 0; i < ListNode.length(); i++) {
			Node n = (Node) ListNode.elementAt(i);
			nba += n.numberArcs();
		}
		return nba;
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

	public void delaunay () {

		Triangle supertriangle = supertri(getPosXMax(), getPosYMax(), 1);

		IList triangles = new LinkedList();
		triangles.insertAt(0, supertriangle);
		Iterator iterNode = ListNode.iterator();

		while (iterNode.hasNext()) {

			Node n = (Node) iterNode.next();

			IList englobant = new LinkedList();
			IList suppress = new LinkedList();

			Triangle tri;

			Iterator iterTri = triangles.iterator();

			while (iterTri.hasNext()) {
				tri = (Triangle) iterTri.next();

				if (tri.containsPoint(n)) {
					suppress.insertAt(0, tri);
				}
			}

			Iterator iterSup = suppress.iterator();

			while (iterSup.hasNext()) {
				tri = (Triangle) iterSup.next();

				if (!tri.edgeShared(tri.AB, suppress, tri)) {
					englobant.insertAt(0, tri.AB);
				}

				if (!tri.edgeShared(tri.AC, suppress, tri)) {
					englobant.insertAt(0, tri.AC);
				}

				if (!tri.edgeShared(tri.BC, suppress, tri)) {
					englobant.insertAt(0, tri.BC);
				}
			}

			iterSup = suppress.iterator();

			while (iterSup.hasNext()) {
				triangles.remove(iterSup.next());
			}

			Iterator iterEngl = englobant.iterator();

			while (iterEngl.hasNext()) {
				Arc a = (Arc) iterEngl.next();
				Node Pt1 = a.dest1;
				Node Pt2 = a.dest2;

				Triangle newTri = new Triangle(Pt1, Pt2, n);
				triangles.insertAt(0, newTri);
			}
		}
		LinkedList suppress = new LinkedList();

		for (int i = 0; i < triangles.length(); i++) {
			Triangle tri = (Triangle) triangles.elementAt(i);

			iterNode = ListNode.iterator();

			while (iterNode.hasNext()) {
				Node n = (Node) iterNode.next();

				if (tri.containsPoint(n)) {
					suppress.insertAt(0, tri);
				}
			}
		}

		Iterator iterSup = suppress.iterator();

		while (iterSup.hasNext()) {
			Triangle sup = (Triangle) iterSup.next();
			triangles.remove(sup);
		}

		Iterator iterTri = triangles.iterator();

		while (iterTri.hasNext()) {
			Triangle tri = (Triangle) iterTri.next();

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

	double weight_of_tree () {
		double weight = 0;

		for (int i = 0; i < ListNode.length(); i++) {
			weight += ((Node) ListNode.elementAt(i)).dijk_pds;
		}
		return weight;
	}

	Graph dijkstra (Node n) {
		ArrayList vis = new ArrayList();

		Iterator iterNode = ListNode.iterator();

		while (iterNode.hasNext()) {
			Node init = (Node) iterNode.next();
			vis.insertAt(0, init);
		}

		ArrayList file = new ArrayList();
		file.insertAt(0, n);

		int ind = 1;

		while (ind != 0) {
			Node current = (Node) file.removeAt(ind - 1);
			ind--;

			if (vis.contains(current)) {
				vis.remove(current);

				if (n == current) {
					current.dijk_pds = 0;
					current.dijk_proch = null;
				}

				IList ListArc = current.ListArc;
				Iterator iterArc = ListArc.iterator();

				while (iterArc.hasNext()) {
					Arc a = (Arc) iterArc.next();

					if (a.dest1.mid_x == current.mid_x && a.dest1.mid_y == current.mid_y) {

						if (vis.contains(a.dest2)) {
							file.insertAt(ind, a.dest2);
							ind++;
						}

						double dist = current.distance(a.dest2);

						if (!(a.dest2.mid_x == n.mid_x && a.dest2.mid_y == n.mid_y) && a.dest2.dijk_proch == null) {
							a.dest2.dijk_proch = current;
							a.dest2.dijk_pds = current.dijk_pds + dist;
						} else if (!(a.dest2.mid_x == n.mid_x && a.dest2.mid_y == n.mid_y) && a.dest2.dijk_pds > current.dijk_pds + dist) {
							a.dest2.dijk_proch = current;
							a.dest2.dijk_pds = current.dijk_pds + dist;
						}
					} else if (a.dest2.mid_x == current.mid_x && a.dest2.mid_y == current.mid_y) {

						if (vis.contains(a.dest1)) {
							file.insertAt(ind, a.dest1);
							ind++;
						}
						double dist = current.distance(a.dest1);

						if (!(a.dest1.mid_x == n.mid_x && a.dest1.mid_y == n.mid_y) && a.dest1.dijk_proch == null) {
							a.dest1.dijk_proch = current;
							a.dest1.dijk_pds = current.dijk_pds + dist;
						} else if (!(a.dest1.mid_x == n.mid_x && a.dest1.mid_y == n.mid_y) && a.dest1.dijk_pds > current.dijk_pds + dist) {
							a.dest1.dijk_proch = current;
							a.dest1.dijk_pds = current.dijk_pds + dist;
						}
					} else {
						System.out.println("WTF?");
					}

				}
			}
		}
		ArrayList newNodes = new ArrayList();

		for (int i = 0; i < ListNode.length(); i++) {
			Node Newn = ((Node) ListNode.elementAt(i)).copy();
			newNodes.insertAt(i, Newn);
		}

		for (int i = 0; i < newNodes.length(); i++) {
			Node current = (Node) newNodes.elementAt(i);

			for (int j = 0; j < newNodes.length(); j++) {
				Node next = (Node) newNodes.elementAt(j);

				if (current.dijk_proch != null && next.mid_x == current.dijk_proch.mid_x && next.mid_y == current.dijk_proch.mid_y) {
					Arc nA = new Arc(current, next);
					current.dijk_proch = next;
					current.addArc(nA);
					next.addArc(nA);
					break;
				}
			}
		}
		Graph newG = new Graph(newNodes, 0);
		return newG;
	}

	public Graph min_spanning_tree () {
		double min = Double.MAX_VALUE;
		Graph MST = null;

		for (int i = 0; i < ListNode.length(); i++) {
			Node current = (Node) ListNode.elementAt(i);
			Graph dijk = dijkstra(current);
			double weight = dijk.weight_of_tree();

			if (min > weight && dijk.ListNode.length() == this.ListNode.length()) {
				min = weight;
				MST = dijk;
			}

			for (int j = 0; j < ListNode.length(); j++) {
				Node reset = (Node) ListNode.elementAt(j);
				reset.dijk_pds = 0;
				reset.dijk_proch = null;
			}
		}
		return MST;
	}

	public void add_random_arc (Graph g) {
		int nb_add = (int) Math.round((0.15 * numberArc()));

		for (int i = 0; i < nb_add; i++) {
			Arc arc = null;

			while (arc == null) {
				int elem_node = (int) Math.floor(Math.random() * (ListNode.length()));
				Node node = (Node) g.ListNode.elementAt(elem_node);
				int elem_arc = (int) Math.floor(Math.random() * (node.ListArc.length()));
				arc = (Arc) node.ListArc.elementAt(elem_arc);

				if (containsArc(arc.dest1.mid_x, arc.dest1.mid_y, arc.dest2.mid_x, arc.dest2.mid_y)) {
					arc = null;
				}
			}

			Node n1 = findNode(arc.dest1.mid_x, arc.dest1.mid_y);
			Node n2 = findNode(arc.dest2.mid_x, arc.dest2.mid_y);
			Arc na = new Arc(n1, n2);
			n1.addArc(na);
			n2.addArc(na);

		}

	}
}
