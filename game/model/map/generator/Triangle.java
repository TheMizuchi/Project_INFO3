package model.map.generator;

import java.lang.Math;


public class Triangle {

	Node A;
	Node B;
	Node C;
	float centerX;
	float centerY;
	double radius;


	Triangle (Node a, Node b, Node c) {
		A = a;
		B = b;
		C = c;
		centerX = centerCircumscribedX();
		centerY = centerCircumscribedY();

		radius = distance(centerX, centerY, A.mid_x, A.mid_y);
	}

	double distance (float x1, float y1, float x2, float y2) {
		return Math.sqrt(Math.abs((double) x1 - (double) x2) + Math.abs((double) y1 - (double) y2));
	}

	float centerCircumscribedX () {
		float delta = 2 * (A.mid_x * B.mid_y + A.mid_y * C.mid_x + B.mid_x * C.mid_y);

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
		float delta = 2 * (A.mid_x * B.mid_y + A.mid_y * C.mid_x + B.mid_x * C.mid_y);

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
		return distance(centerX, centerY, n.mid_x, n.mid_y) < radius;
	}
}
