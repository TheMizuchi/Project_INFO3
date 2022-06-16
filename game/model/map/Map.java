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

	World world;
	int level;

	int width;
	int height;

	Case[][] grid;
	ArrayList rooms; //Salles présentes dans l'étage


	public Map (World world, int level, int nbRoomsMax) {
		this.world = world;

		this.level = level;

		rooms = new ArrayList();

		IList.Iterator iter = (Iterator) world.rooms.iterator();

		while (iter.hasNext()) {
			Room r = (Room) iter.next();
			r.setUpperLeft(-1, -1);
		}

		placeRoomsRandomlyV2(nbRoomsMax);

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

		placeRoomsRandomly(10);
	}

	//N : nombre de salles standards
	private void placeRoomsRandomlyV2 (int N) {
		Random random = new Random();
		Room spawn = null, boss = null, key = null;
		ArrayList keyRooms = new ArrayList();
		ArrayList standardRooms = new ArrayList();
		int radiusInc = 4;
		double angleInc = 2 * Math.PI / 100;
		int minX = 0, minY = 0, maxX = 0, maxY = 0;
		int faraway = 5;

		IList.Iterator iter = world.rooms.iterator();

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

		//Puis les autres salles
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

}
