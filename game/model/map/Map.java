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

	World world;
	int level;

	int width;
	int height;

	Case[][] grid;
	ArrayList rooms; //Salles présentes dans l'étage


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

		IList.Iterator iter = (Iterator) world.rooms.iterator();

		while (iter.hasNext()) {
			Room r = (Room) iter.next();
			r.setUpperLeft(-1, -1);
		}

		rooms = new ArrayList();

		placeRoomsRandomly(100);
	}

	private void placeRoomsRandomly (int N) {
		Random random = new Random();
		Room spawnRoom = null;
		Room bossRoom = null;
		ArrayList keyRooms = new ArrayList();
		ArrayList standardRooms = new ArrayList();

		for (int i = 0; i < world.rooms.length(); i++) {
			Room r = (Room) world.rooms.elementAt(i);
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
					grid[i + r.getUpperLeftX()][j + r.getUpperLeftY()] = r.getComp()[i][j];
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

	private void corridor (Arc a) {
		Node dest1 = a.first();
		Node dest2 = a.second();

		int distX = dest2.centerX() - dest1.centerX();
		int distY = dest2.centerY() - dest1.centerY();

		double angle;

		if (angle > 45.0 && angle <= 135.0) {
			int posX = dest1.centerX();
			int posY;

			for (posY = dest1.content().getHeight(); posY < dest2.centerY(); posY++) {
				Case tmp = grid[posX][posY];
				grid[posX][posY].type = TileType.FLOOR;
				grid[posX - 1][posY].type = TileType.WALL;
				grid[posX + 1][posY].type = TileType.WALL;

				if (tmp.type == TileType.WALL && grid[posX][posY + 1].type == TileType.FLOOR) {
					return;
				}
			}
			
		}

		if (angle > 135.0 && angle <= 180 || angle < -135.0 && angle >= -180) {

		}

		if (angle <= 45.0 && angle >= -45) {

		}

		if (angle < -45.0 && angle >= -135) {

		}
	}

	private void generate_corridors () {
		Graph G = new Graph(rooms);
		G.delaunay();
		Graph corGraph = G.min_spanning_tree();
		corGraph.add_random_arc(G);

		for (int i = 0; i < corGraph.ListNode.length(); i++) {
			Node n = (Node) corGraph.ListNode.elementAt(i);

		}
	}

}
