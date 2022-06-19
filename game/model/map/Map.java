package model.map;

import java.util.Random;

import edu.polytech.oop.collections.ArrayList;
import edu.polytech.oop.collections.ICollection.Iterator;
import edu.polytech.oop.collections.IList;
import model.map.generator.Arc;
import model.map.generator.Corridor;
import model.map.generator.Graph;
import model.map.generator.Node;
import model.map.generator.RectangleCollisionTEMPORAIRE;
import model.map.generator.RectangleTEMPORAIRE;
import model.map.generator.Room;
import model.map.generator.RoomType;


public class Map {

	private World world;
	private int level;

	private int width;
	private int height;

	private Case[][] grid;
	private ArrayList rooms; //Salles présentes dans l'étage


	public Map (World world, int level, int nbRoomsMax) {
		this.world = world;

		this.level = level;

		rooms = new ArrayList();

		IList.Iterator iter = (Iterator) world.getRooms().iterator();

		while (iter.hasNext()) {
			Room r = (Room) iter.next();
			r.setUpperLeft(-1, -1);
		}

		placeRoomsRandomly(nbRoomsMax);

		grid = new Case[width][height];

		for (int i = 0; i < width; i++) {

			for (int j = 0; j < height; j++) {
				grid[i][j] = new Case();
			}
		}

		iter = rooms.iterator();

		while (iter.hasNext()) {
			Room r = (Room) iter.next();

			for (int i = 0; i < r.getWidth(); i++) {

				for (int j = 0; j < r.getHeight(); j++) {
					grid[i + r.getUpperLeftX()][j + r.getUpperLeftY()] = r.getComp()[i][j];
				}
			}
		}

	}

	//N : nombre de salles standards
	private void placeRoomsRandomly (int N) {
		Random random = new Random();
		Room spawn = null, boss = null, key = null;
		ArrayList keyRooms = new ArrayList();
		ArrayList standardRooms = new ArrayList();
		int radiusInc = 4;
		double angleInc = 2 * Math.PI / 100;
		int minX = 0, minY = 0, maxX = 0, maxY = 0;
		int faraway = 5;

		IList.Iterator iter = world.getRooms().iterator();

		while (iter.hasNext()) {
			Room r = (Room) iter.next();
			RoomType t = r.getType();

			switch (t) {
				case SPAWN:
					spawn = r;
					break;
				case BOSS1:
					if (level == 1)
						boss = r;
					break;
				case BOSS2:
					if (level == 2)
						boss = r;
					break;
				case BOSS3:
					if (level == 3)
						boss = r;
					break;
				case KEY:
					keyRooms.insertAt(keyRooms.length(), r);
					break;
				case FIGHT:
					standardRooms.insertAt(standardRooms.length(), r);
					break;
				case ENIGMA:
					standardRooms.insertAt(standardRooms.length(), r);
					break;
			}
		}

		if (spawn == null)
			throw new IllegalStateException("There is no spawn room");

		//Placement spawn
		spawn.setUpperLeft(-spawn.getWidth() / 2, -spawn.getHeight() / 2);
		rooms.insertAt(rooms.length(), spawn);
		minX = spawn.getUpperLeftX();
		minY = spawn.getUpperLeftY();
		maxX = spawn.getUpperLeftX() + spawn.getWidth();
		maxY = spawn.getUpperLeftY() + spawn.getHeight();

		//Placement salles standards
		int radius = radiusInc;
		double angle = 0;
		int placed = 0;
		int k = random.nextInt(standardRooms.length());

		while (standardRooms.length() != 0 && placed < N) {
			Room r = (Room) standardRooms.elementAt(k);
			r.setUpperLeft((int) (radius * Math.cos(angle) - r.getWidth() / 2), (int) (radius * Math.sin(angle) - r.getHeight() / 2));

			if (!collision(r)) {
				rooms.insertAt(rooms.length(), r);
				standardRooms.remove(r);
				if (standardRooms.length() != 0)
					k = random.nextInt(standardRooms.length());
				radius = radiusInc;
				placed++;
				if (r.getUpperLeftX() < minX)
					minX = r.getUpperLeftX();
				if (r.getUpperLeftY() < minY)
					minY = r.getUpperLeftY();
				if (r.getUpperLeftX() + r.getWidth() > maxX)
					maxX = r.getUpperLeftX() + r.getWidth();
				if (r.getUpperLeftY() + r.getHeight() > maxY)
					maxY = r.getUpperLeftY() + r.getHeight();
			} else {
				r.setUpperLeft(-1, -1);
			}

			angle += angleInc;

			if (angle > 2 * Math.PI) {
				angle = 0;
				radius += radiusInc;
			}
		}

		//Placement clef et boss
		radius = radiusInc * faraway;
		angle = 0;
		k = random.nextInt(keyRooms.length());
		key = (Room) keyRooms.elementAt(k);
		placed = 0;

		while (placed == 0) {
			key.setUpperLeft((int) (radius * Math.cos(angle) - key.getWidth() / 2), (int) (radius * Math.sin(angle) - key.getHeight() / 2));

			if (!collision(key)) {
				rooms.insertAt(rooms.length(), key);
				placed = 1;
				if (key.getUpperLeftX() < minX)
					minX = key.getUpperLeftX();
				if (key.getUpperLeftY() < minY)
					minY = key.getUpperLeftY();
				if (key.getUpperLeftX() + key.getWidth() > maxX)
					maxX = key.getUpperLeftX() + key.getWidth();
				if (key.getUpperLeftY() + key.getHeight() > maxY)
					maxY = key.getUpperLeftY() + key.getHeight();
			} else {
				key.setUpperLeft(-1, -1);
			}
			angle += angleInc;

			if (angle > 2 * Math.PI) {
				angle = 0;
				radius += radiusInc;
			}
		}

		radius = radiusInc * faraway;
		angle = 0;
		placed = 0;

		while (placed == 0) {
			boss.setUpperLeft((int) (radius * Math.cos(angle) - boss.getWidth() / 2), (int) (radius * Math.sin(angle) - boss.getHeight() / 2));

			if (!collision(boss)) {
				rooms.insertAt(rooms.length(), boss);
				placed = 1;
				if (boss.getUpperLeftX() < minX)
					minX = boss.getUpperLeftX();
				if (boss.getUpperLeftY() < minY)
					minY = boss.getUpperLeftY();
				if (boss.getUpperLeftX() + boss.getWidth() > maxX)
					maxX = boss.getUpperLeftX() + boss.getWidth();
				if (boss.getUpperLeftY() + boss.getHeight() > maxY)
					maxY = boss.getUpperLeftY() + boss.getHeight();
			} else {
				boss.setUpperLeft(-1, -1);
			}
			angle += angleInc;

			if (angle > 2 * Math.PI) {
				angle = 0;
				radius += radiusInc;
			}
		}

		//On recale tout sur des coords positives
		iter = rooms.iterator();

		while (iter.hasNext()) {
			Room r = (Room) iter.next();
			r.setUpperLeft(r.getUpperLeftX() + max(-minX, maxX), r.getUpperLeftY() + max(-minY, maxY));
		}

		width = max(-minX, maxX) * 2;
		height = max(-minY, maxY) * 2;

	}

	private int max (int a, int b) {
		if (a < b)
			return b;
		else
			return a;
	}

	private void expand () {

	}

	//true si room a une collision avec au moins une des autres
	private boolean collision (Room room) {
		if (room == null)
			return true;

		int margin = 2; //Marge dans la collision pour laisser de l'espace entre les salles

		IList.Iterator iter = rooms.iterator();

		while (iter.hasNext()) {
			Room r = (Room) iter.next();
			int x1 = r.getUpperLeftX() - margin;
			int y1 = r.getUpperLeftY() - margin;
			int w1 = r.getWidth() + 2 * margin;
			int h1 = r.getHeight() + 2 * margin;
			int x2 = room.getUpperLeftX() - margin;
			int y2 = room.getUpperLeftY() - margin;
			int w2 = room.getWidth() + 2 * margin;
			int h2 = room.getHeight() + 2 * margin;
			RectangleTEMPORAIRE r1 = new RectangleTEMPORAIRE(x1, y1, w1, h1);
			RectangleTEMPORAIRE r2 = new RectangleTEMPORAIRE(x2, y2, w2, h2);
			RectangleCollisionTEMPORAIRE col = new RectangleCollisionTEMPORAIRE(r1, r2);

			if (col.collides()) {
				return true;
			}
		}

		return false;
	}

	public Case[][] getCases () {
		return grid;
	}

	public int getWidth () {
		return this.width;
	}

	public int getHeight () {
		return this.height;
	}

	private void corridorOnMap (Corridor c) {

		Iterator iterPath = c.getPath().iterator();
		Node n1 = (Node) iterPath.next();
		Node begin = n1;

		int posX = n1.centerX();
		int posY = n1.centerY();

		while (iterPath.hasNext()) {
			Node n2 = (Node) iterPath.next();

			if (n1.centerX() == n2.centerX()) {

				for (posY = n1.centerY(); posY != n2.centerY(); posY += Integer.signum(n2.centerY() - n1.centerY())) {
					grid[posX][posY].setType(TileType.VOID);
				}
			} else {

				for (posX = n1.centerX(); posX != n2.centerX(); posX += Integer.signum(n2.centerX() - n1.centerX())) {
					grid[posX][posY].setType(TileType.VOID);
				}

			}
			n1 = n2;

		}
		int distX = n1.centerX()-begin.centerX();
		int distY = n1.centerY()-begin.centerY();
				grid[posX][posY].setType(TileType.VOID);
	}

	public void corridors () {
		Graph g = new Graph(rooms);
		g.delaunay();
		Graph MST = g.min_spanning_tree();
		MST.add_random_arc(g);
		for (int i = 0; i < MST.ListNode.length(); i++) {
			Node n = (Node) MST.ListNode.elementAt(i);
			System.out.println("From node (" + n.centerX() + ", " + n.centerY() + ")");

			for (int j = 0; j < n.numberArcs(); j++) {
				Arc a = (Arc) n.getListArc().elementAt(j);
				System.out.println("( " + a.first().centerX() + ", " + a.first().centerY() + ") to (" + a.second().centerX() + ", " + a.second().centerY() + ")");
			}
		}

		IList nodes = MST.ListNode;
		Iterator iterNode = nodes.iterator();

		while (iterNode.hasNext()) {
			Node n = (Node) iterNode.next();
			IList arcs = n.getListArc();
			Iterator iterArc = arcs.iterator();

			while (iterArc.hasNext()) {
				Arc a = (Arc) iterArc.next();

				if (!a.getDone()) {

					Corridor c = new Corridor(a, rooms);

					a.setDone();
					this.corridorOnMap(c);
				}
			}
		}

	}

}
