package model.map.generator;

import edu.polytech.oop.collections.IList;
import edu.polytech.oop.collections.ArrayList;
import edu.polytech.oop.collections.ICollection.Iterator;


public class Corridor {

	IList path;


	public Corridor (Arc a, IList rooms) {
		ArrayList pile = new ArrayList();
		path = new ArrayList();

		Node dest1 = a.dest1;
		Node dest2 = a.dest2;
		
		boolean north;

		Node n1;
		Node n2;
		Node n3;

		Node D;
		Node E;
		Node F;

		Node begin;
		Node end;

		int distX = dest2.centerX() - dest1.centerX();
		int distY = dest2.centerY() - dest1.centerY();

		int posR1X = dest1.content().getUpperLeftX();
		int posR1Y = dest1.content().getUpperLeftY();
		int R1W = dest1.content().getWidth();
		int R1H = dest1.content().getHeight();
 
		int posR2X = dest2.content().getUpperLeftX();
		int posR2Y = dest2.content().getUpperLeftY();
		int R2W = dest2.content().getWidth();
		int R2H = dest2.content().getHeight();

		if (Math.abs(distX) < Math.abs(distY)) {

			if (distY > 0) {
		 		n1 = new Node(dest1.centerX(), posR1Y + R1H - 1);
				n2 = new Node(dest1.centerX(), posR2Y);
				n3 = new Node(dest2.centerX(), posR2Y);
			} else {
				n1 = new Node(dest1.centerX(), posR1Y);
				n2 = new Node(dest1.centerX(), posR2Y + R2H - 1);
				n3 = new Node(dest2.centerX(), posR2Y + R2H - 1);
			}
			north = true;
		} else if (Math.abs(distX) > Math.abs(distY)) {

			if (distX > 0) {
				n1 = new Node(posR1X + R1W - 1, dest1.centerY());
				n2 = new Node(posR1X + R1W - 1, dest2.centerY());
				n3 = new Node(posR2X, dest2.centerY());
			} else {
				n1 = new Node(posR1X, dest1.centerY());
				n2 = new Node(posR1X, dest2.centerY());
				n3 = new Node(posR2X + R2W - 1, dest2.centerY());
			}
			north = false;

		} else {
			if(distY > 0) {
		 		n1 = new Node(dest1.centerX(), posR1Y + R1H - 1);
				n2 = new Node(dest1.centerX(), posR2Y);
	 			n3 = new Node(dest2.centerX(), posR2Y);
			}
			else {
				n1 = new Node(dest1.centerX(), posR1Y);
				n2 = new Node(dest1.centerX(), posR2Y + R2H - 1);
				n3 = new Node(dest2.centerX(), posR2Y + R2H - 1);
			}
			north = true;
		}

		begin = n1;
		end = n3;

		pile.insertAt(0, n1);
		pile.insertAt(1, n2);
		pile.insertAt(2, n3);
 
		int indP = 3;

		while (indP > 2) {
			n1 = (Node) pile.removeAt(0);
			indP--;
		 	n2 = (Node) pile.removeAt(0);
	 		indP--;
	 		n3 = (Node) pile.removeAt(0);
		 	indP--;
 
			boolean valid12 = validPath(n1, n2, rooms, begin, end);
		 	boolean valid23 = validPath(n2, n3, rooms, begin, end);
 
	 		if (valid12 && valid23) {
 				path.insertAt(path.length(), n1);
				path.insertAt(path.length(), n2);
				pile.insertAt(0, n3);
				indP++;
			} else {
				if(!valid23 && north && !(n3.mid_x == end.mid_x && n3.mid_y == end.mid_y) && PointInsideRoom(n3.mid_x, (n1.mid_y + n3.mid_y) / 2, rooms)) {
					F = new Node(n3.mid_y+(n1.mid_x - n3.mid_x) / 2, n3.mid_y+(n3.mid_y - n1.mid_y)/2);
				}
				else if (!north && !(n3.mid_x == end.mid_x && n3.mid_y == end.mid_y) && PointInsideRoom((n1.mid_x + n3.mid_x) / 2, n3.mid_y, rooms)) {
					F = new Node(n3.mid_y+(n1.mid_x - n3.mid_x) / 2, n3.mid_y+(n3.mid_y - n1.mid_y)/2);
				}
				else{
					F = new Node((n1.mid_x + n3.mid_x) / 2, (n1.mid_y + n3.mid_y) / 2);
				}
 
				if (north) {
					D = new Node(n1.mid_x, F.mid_y);
					E = new Node(n3.mid_x, F.mid_y);

				} else {
					D = new Node(F.mid_x, n1.mid_y);
					E = new Node(F.mid_x, n3.mid_y);
				}

				pile.insertAt(0, n3);
			 	indP++;
				pile.insertAt(0, E);
	 			indP++;
				if(distX == distY) {

					pile.insertAt(0, F);
				 	indP++;
				}
		 		pile.insertAt(0, D);
				indP++;
				pile.insertAt(0, n1);
				indP++;

			}
		}
		Iterator iter = pile.iterator();

		while (iter.hasNext()) {
			path.insertAt(path.length(), iter.next());
		}
	}

	public IList getPath () {
		return path;
	}

	private boolean PointInsideRoom (int x, int y, IList rooms) {
		Iterator iter = rooms.iterator();
 
		while (iter.hasNext()) {
			Room r = (Room) iter.next();

			if (r.containsPoint(x, y)) {
				return true;
			}
		}
		return false;
	}

	private boolean validPath (Node n1, Node n2, IList rooms, Node begin, Node end) {
		int posX;
		int posY;

		if (n1.mid_x == n2.mid_x) {
			posX = n1.mid_x;
			int distY = n2.mid_y - n1.mid_y;

			for (posY = n1.mid_y; posY != n2.mid_y + Integer.signum(distY); posY += Integer.signum(distY)) {

				if (!(begin.mid_x == posX && begin.mid_y == posY) && !(end.mid_x == posX && end.mid_y == posY)) {

					if (PointInsideRoom(posX, posY, rooms)) {
						return false;
					}
				}
			}
			return true;
		}

		if (n1.mid_y == n2.mid_y) {
			posY = n1.mid_y;
			int distX = n2.mid_x - n1.mid_x;

			for (posX = n1.mid_x; posX != n2.mid_x + Integer.signum(distX); posX += Integer.signum(distX)) {

				if (!(begin.mid_x == posX && begin.mid_y == posY) && !(end.mid_x == posX && end.mid_y == posY)) {
 
					if (PointInsideRoom(posX, posY, rooms)) {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}

}