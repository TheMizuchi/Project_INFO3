package model.map;

import java.util.Random;

import edu.polytech.oop.collections.ArrayList;
import edu.polytech.oop.collections.ICollection.Iterator;
import edu.polytech.oop.collections.IList;
import model.map.generator.Arc;
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


	public Map (World world, int w, int h, int level) {
		this.world = world;

		this.width = w;
		this.height = h;
		this.level = level;

		grid = new Case[w][h];

		for (int i = 0; i < w; i++) {

			for (int j = 0; j < h; j++) {
				grid[i][j] = new Case();
			}
		}

		IList.Iterator iter = (Iterator) world.getRooms().iterator();

		while (iter.hasNext()) {
			Room r = (Room) iter.next();
			r.setUpperLeft(-1, -1);
		}

		rooms = new ArrayList();

		placeRoomsRandomly(100);
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

	private void placeRoomsRandomly (int N) {
		Random random = new Random();
		Room spawnRoom = null;
		Room bossRoom = null;
		ArrayList keyRooms = new ArrayList();
		ArrayList standardRooms = new ArrayList();

		for (int i = 0; i < world.getRooms().length(); i++) {
			Room r = (Room) world.getRooms().elementAt(i);
			RoomType t = r.getType();

			switch (t) {
				case SPAWN:
					spawnRoom = r;
					break;
				case KEY:
					keyRooms.insertAt(keyRooms.length(), r);
					break;
				case BOSS1:
					if (level == 1)
						bossRoom = r;
					break;
				case BOSS2:
					if (level == 2)
						bossRoom = r;
					break;
				case BOSS3:
					if (level == 3)
						bossRoom = r;
					break;
				case FIGHT:
					standardRooms.insertAt(standardRooms.length(), r);
					break;
				case ENIGMA:
					standardRooms.insertAt(standardRooms.length(), r);
					break;
				default:
					throw new IllegalStateException("Unknown RoomType");
			}
		}

		if (keyRooms.length() == 0)
			throw new IllegalStateException("There is no key room");
		if (standardRooms.length() == 0)
			throw new IllegalStateException("There is no standard room");
		if (spawnRoom == null)
			throw new IllegalStateException("There is no spawn room");
		if (bossRoom == null)
			throw new IllegalStateException("There is no boss room");

		//On place d'abord la salle du spawn
		spawnRoom.setUpperLeft(width / 2, height / 2);
		rooms.insertAt(rooms.length(), spawnRoom);

		int k;
		int location = random.nextInt(4);

		//Puis la salle à clef
		k = random.nextInt(keyRooms.length());
		Room keyRoom = (Room) keyRooms.elementAt(k);
		placeSpecialRoom(keyRoom, location);
		rooms.insertAt(rooms.length(), keyRoom);

		//Puis la salle de boss
		placeSpecialRoom(bossRoom, (location + 2) % 4);
		rooms.insertAt(rooms.length(), bossRoom);

		//Ensuite les autres salles
		int n = 0;
		k = random.nextInt(standardRooms.length());
		Room room = (Room) standardRooms.elementAt(k);

		while (n < N && standardRooms.length() != 0) {
			int x = random.nextInt(width - room.getWidth());
			int y = random.nextInt(height - room.getHeight());
			room.setUpperLeft(x, y);

			if (!collision(room)) {
				rooms.insertAt(rooms.length(), room);
				standardRooms.remove(room);

				try {
					k = random.nextInt(standardRooms.length());
					room = (Room) standardRooms.elementAt(k);
				}
				catch (IllegalArgumentException e) {}
			} else {
				room.setUpperLeft(-1, -1);
			}
			n++;
		}

		//Enfin on remplit la grille
		IList.Iterator iter = rooms.iterator();

		while (iter.hasNext()) {
			Room r = (Room) iter.next();

			for (int i = 0; i < r.getWidth(); i++) {

				for (int j = 0; j < r.getHeight(); j++) {
					grid[i + r.getUpperLeftX()][j + r.getUpperLeftY()].setType(r.getComp()[i][j].getType());
				}
			}
		}

	}

	private void placeSpecialRoom (Room room, int location) {
		Random random = new Random();
		int x, y;

		//Permet d'éloigner la clef du boss
		switch (location) {
			case 0:
				x = random.nextInt(width / 4 - room.getWidth());
				y = random.nextInt(height / 4 - room.getHeight());
				break;
			case 1:
				x = random.nextInt(width / 4 - room.getWidth()) + 3 * width / 4;
				y = random.nextInt(height / 4 - room.getHeight());
				break;
			case 2:
				x = random.nextInt(width / 4 - room.getWidth()) + 3 * width / 4;
				y = random.nextInt(height / 4 - room.getHeight()) + 3 * height / 4;
				break;
			case 3:
				x = random.nextInt(width / 4 - room.getWidth());
				y = random.nextInt(height / 4 - room.getHeight()) + 3 * height / 4;
				break;
			default:
				throw new IllegalStateException("Unknown location");
		}

		room.setUpperLeft(x, y);
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

	private boolean PointInsideRoom (int x, int y) {
		Iterator iter = rooms.iterator();

		while (iter.hasNext()) {
			Room r = (Room) iter.next();

			if (r.containsPoint(x, y)) {
				return true;
			}
		}
		return false;
	}

	private void corridor (Arc a) {
		Node dest1 = a.first();
		Node dest2 = a.second();

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

		int posX;
		int posY;

		//Si les salles sont assez proches faire un couloir verticale
		if (Math.abs(distX) < R1W / 2 - 1 && Math.abs(distX) < R2W / 2 - 1) {

			posX = R1W / 2 + dest1.content().getUpperLeftX() + distX / 2;

			if (distY > 0) {

				for (posY = posR1Y; posY < posR2Y + R2H; posY++) {
					grid[posX][posY].setType(TileType.VOID);
				}
			} else {

				for (posY = posR1Y + R1H; posY > posR2Y; posY--) {
					grid[posX][posY].setType(TileType.VOID);
				}
			}
		}
		//Si les salles sont assez proches faire un couloir horizontale
		else if (Math.abs(distY) < R1H / 2 && Math.abs(distY) < R2H / 2) {

			posY = R1H / 2 + posR1Y + distY / 2;

			if (distX > 0) {

				for (posX = posR1X; posX < posR2X + R2W; posX++) {
					grid[posX][posY].setType(TileType.VOID);
				}
			} else {

				for (posX = posR1X + R1W; posX > posR2X; posX--) {
					grid[posX][posY].setType(TileType.VOID);
				}
			}
		}

		else {

			if (Math.abs(distY) <= Math.abs(distX) && (!PointInsideRoom(dest1.centerX(), dest2.centerY())) || Math.abs(distY) > Math.abs(distX) && !PointInsideRoom(dest2.centerX(), dest1.centerY())) {
				posY = dest1.centerY();

				if (distX < 0 && distY < 0) {

					for (posX = posR1X; posX > dest2.centerX(); posX--) {
						grid[posX][posY].setType(TileType.VOID);
					}

					for (posY = dest1.centerY(); posY > posR2Y + R2H; posY--) {
						grid[posX][posY].setType(TileType.VOID);
					}

				} else if (distX > 0 && distY < 0) {

					for (posX = posR1X + R1W; posX < dest2.centerX(); posX++) {
						grid[posX][posY].setType(TileType.VOID);
					}

					for (posY = dest1.centerY(); posY > posR2Y + R2H; posY--) {
						grid[posX][posY].setType(TileType.VOID);
					}

				} else if (distX < 0 && distY > 0) {

					for (posX = posR1X; posX > dest2.centerX(); posX--) {
						grid[posX][posY].setType(TileType.VOID);
					}

					for (posY = dest1.centerY(); posY < posR2Y + R2H; posY++) {
						grid[posX][posY].setType(TileType.VOID);
					}

				} else {

					for (posX = posR1X + R1W; posX < dest2.centerX(); posX++) {
						grid[posX][posY].setType(TileType.VOID);
					}

					for (posY = dest1.centerY(); posY < posR2Y + R2H; posY++) {
						grid[posX][posY].setType(TileType.VOID);
					}

				}
			} else if (Math.abs(distY) < Math.abs(distX) && (!PointInsideRoom(dest1.centerX(), dest2.centerY())) || Math.abs(distY) >= Math.abs(distX) && !PointInsideRoom(dest2.centerX(), dest1.centerY())) {
				posX = dest1.centerX();

				if (distX < 0 && distY < 0) {

					for (posY = posR1Y + R1H; posY > dest2.centerY(); posY--) {
						grid[posX][posY].setType(TileType.VOID);
					}

					for (posX = dest1.centerX(); posX > posR2X + R2W; posX--) {
						grid[posX][posY].setType(TileType.VOID);
					}

				} else if (distX > 0 && distY < 0) {

					for (posY = posR1Y + R1H; posY > dest2.centerY(); posY--) {
						grid[posX][posY].setType(TileType.VOID);
					}

					for (posX = dest1.centerX(); posX < posR2X + R2W; posX++) {
						grid[posX][posY].setType(TileType.VOID);
					}

				} else if (distX < 0 && distY > 0) {

					for (posY = posR1Y + R1H; posY < dest2.centerY(); posY++) {
						grid[posX][posY].setType(TileType.VOID);
					}

					for (posX = dest1.centerX(); posX > posR2X + R2W; posX--) {
						grid[posX][posY].setType(TileType.VOID);
					}

				} else {

					for (posY = posR1Y + R1H; posY < dest2.centerY(); posY++) {
						grid[posX][posY].setType(TileType.VOID);
					}

					for (posX = dest1.centerX(); posX < posR2X + R2W; posX++) {
						grid[posX][posY].setType(TileType.VOID);
					}
				}

			} else {
				Node milieu = new Node(new Room(dest1.centerX() + distX / 2, dest1.centerY() + distY / 2));
				Arc mid1 = new Arc(dest1, milieu);
				Arc mid2 = new Arc(milieu, dest2);
				corridor(mid1);
				corridor(mid2);
			}
		}
		a.setDone();

	}

	public void generate_corridors () {
		Graph G = new Graph(rooms);
		G.delaunay();
		Graph corGraph = G.min_spanning_tree();
		corGraph.add_random_arc(G);
		Iterator iterNode = corGraph.ListNode.iterator();

		while (iterNode.hasNext()) {
			Node n = (Node) iterNode.next();
			Iterator iterArc = n.getListArc().iterator();

			while (iterArc.hasNext()) {
				Arc a = (Arc) iterArc.next();

				if (!a.getDone()) {
					corridor(a);
				}
			}

		}
	}

}
