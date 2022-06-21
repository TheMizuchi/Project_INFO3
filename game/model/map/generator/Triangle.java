package model.map.generator;

import java.lang.Math;

import edu.polytech.oop.collections.IList;


public class Triangle {

	Node A;
	Node B;
	Node C;
	Arc AB;
	Arc AC;
	Arc BC;

	float centerX;
	float centerY;
	double radius;


	Triangle (Node a, Node b, Node c) {
		A = a;
		B = b;
		C = c;
		AB = new Arc(A, B);
		AC = new Arc(A, C);
		BC = new Arc(B, C);

		centerX = centerCircumscribedX();
		centerY = centerCircumscribedY();

		radius = distance(centerX, centerY, A.mid_x, A.mid_y);
	}

	double distance (float x1, float y1, float x2, float y2) {
		double x = Math.abs((double) x1 - (double) x2);
		double y = Math.abs((double) y1 - (double) y2);
		return Math.sqrt(x * x + y * y);
	}

	float centerCircumscribedX () {
		float delta = 2 * (A.mid_x * B.mid_y + A.mid_y * C.mid_x + B.mid_x * C.mid_y - C.mid_x * B.mid_y - B.mid_x * A.mid_y - A.mid_x * C.mid_y);

		float x = (A.mid_x * A.mid_x + A.mid_y * A.mid_y) * B.mid_y;
		x += (B.mid_x * B.mid_x + B.mid_y * B.mid_y) * C.mid_y;
		x += (C.mid_x * C.mid_x + C.mid_y * C.mid_y) * A.mid_y;

		x -= (C.mid_x * C.mid_x + C.mid_y * C.mid_y) * B.mid_y;
		x -= (B.mid_x * B.mid_x + B.mid_y * B.mid_y) * A.mid_y;
		x -= (A.mid_x * A.mid_x + A.mid_y * A.mid_y) * C.mid_y;

		x = (float) (x * (1.0 / delta));
		return x;
	}

	float centerCircumscribedY () {
		float delta = 2 * (A.mid_x * B.mid_y + A.mid_y * C.mid_x + B.mid_x * C.mid_y - C.mid_x * B.mid_y - B.mid_x * A.mid_y - A.mid_x * C.mid_y);

		float y = (A.mid_x * A.mid_x + A.mid_y * A.mid_y) * B.mid_x;
		y += (B.mid_x * B.mid_x + B.mid_y * B.mid_y) * C.mid_x;
		y += (C.mid_x * C.mid_x + C.mid_y * C.mid_y) * A.mid_x;

		y -= (C.mid_x * C.mid_x + C.mid_y * C.mid_y) * B.mid_x;
		y -= (B.mid_x * B.mid_x + B.mid_y * B.mid_y) * A.mid_x;
		y -= (A.mid_x * A.mid_x + A.mid_y * A.mid_y) * C.mid_x;

		y = (float) (y * (-1.0 / delta));
		return y;
	}

	boolean containsPoint (Node n) {
		return ((A.mid_x * (B.mid_y - C.mid_y) + B.mid_x * (C.mid_y - A.mid_y) + C.mid_x * (A.mid_y - B.mid_y)) != 0) && (distance(centerX, centerY, n.mid_x, n.mid_y) < radius);
	}

	boolean edgeShared (Arc seg, IList triangles, Triangle current) {
		Node n1 = seg.first();
		Node n2 = seg.second();
		boolean sameA = false;
		boolean sameB = false;
		boolean sameC = false;

		for (int i = 0; i < triangles.length(); i++) {
			Triangle tri = (Triangle) triangles.elementAt(i);

			if (tri != current) {

				Node A = tri.A;
				Node B = tri.B;
				Node C = tri.C;

				sameA = (A.mid_x == n1.mid_x && A.mid_y == n1.mid_y) || (A.mid_x == n2.mid_x && A.mid_y == n2.mid_y);
				sameB = (B.mid_x == n1.mid_x && B.mid_y == n1.mid_y) || (B.mid_x == n2.mid_x && B.mid_y == n2.mid_y);
				sameC = (C.mid_x == n1.mid_x && C.mid_y == n1.mid_y) || (C.mid_x == n2.mid_x && C.mid_y == n2.mid_y);

				if (sameA && sameB || sameA && sameC || sameB && sameC) {
					return true;
				}
			}
		}
		return false;
	}
}
