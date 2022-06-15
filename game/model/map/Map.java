package model.map;

import java.util.ArrayList;
import java.util.Random;

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
	ArrayList<Room> rooms; //Salles présentes dans l'étage


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

		for (Room r : world.rooms)
			r.setUpperLeft(-1, -1);

		rooms = new ArrayList<Room>();

		placeRoomsRandomly(100);
	}

	private void placeRoomsRandomly (int N) {
		Random random = new Random();
		Room bossRoom = null;
		ArrayList<Room> keyRooms = new ArrayList<Room>();
		ArrayList<Room> standardRooms = new ArrayList<Room>();
		bossRoom = fillLists(keyRooms, standardRooms);

		int k, location;
		location = random.nextInt(4);

		// On place d'abord la salle à clef
		k = random.nextInt(keyRooms.size());
		Room keyRoom = keyRooms.get(k);
		placeSpecialRoom(keyRoom, location);
		rooms.add(keyRoom);

		//Puis la salle de boss
		placeSpecialRoom(bossRoom, (location + 2) % 4);
		rooms.add(bossRoom);

		//Ensuite les autres salles
		int n = 0;
		k = random.nextInt(standardRooms.size());
		Room room = standardRooms.get(k);

		while (n < N && standardRooms.size() != 0) {
			int x = random.nextInt(width - room.getWidth());
			int y = random.nextInt(height - room.getHeight());
			room.setUpperLeft(x, y);

			if (!collision(room)) {
				rooms.add(room);
				standardRooms.remove(room);

				try {
					k = random.nextInt(standardRooms.size());
					room = standardRooms.get(k);
				}
				catch (IllegalArgumentException e) {}
			} else {
				room.setUpperLeft(-1, -1);
			}
			n++;
		}

		//Enfin on remplit la grille
		for (Room r : rooms) {

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

	//Retourne la salle du boss
	private Room fillLists (ArrayList<Room> keyRooms, ArrayList<Room> standardRooms) {
		ArrayList<Room> worldRooms = world.rooms;
		Room bossRoom = null;

		for (int i = 0; i < worldRooms.size(); i++) {
			Room r = worldRooms.get(i);
			RoomType t = r.getType();

			switch (t) {
				case KEY:
					keyRooms.add(r);
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
					standardRooms.add(r);
					break;
				case ENIGMA:
					standardRooms.add(r);
					break;
				default:
					throw new IllegalStateException("Unknown RoomType");
			}
		}

		if (keyRooms.size() == 0)
			throw new IllegalStateException("There is no key room");
		if (standardRooms.size() == 0)
			throw new IllegalStateException("There is no standard room");
		if (bossRoom == null)
			throw new IllegalStateException("There is no boss room");

		return bossRoom;
	}

	private boolean collision (Room room) {
		if (room == null)
			return true;

		int margin = 2; //Marge dans la collision pour laisser de l'espace entre les salles

		for (Room r : rooms) {
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

}
