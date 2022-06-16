package model.map;

import java.util.Random;

import edu.polytech.oop.collections.ArrayList;
import edu.polytech.oop.collections.ICollection.Iterator;
import edu.polytech.oop.collections.IList;
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

}
