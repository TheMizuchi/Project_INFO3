package model.map.generator;

import java.util.Random;

import edu.polytech.oop.collections.ICollection;
import edu.polytech.oop.collections.LinkedList;
import model.Model;
import model.entity.Door;
import model.entity.Entity;
import model.entity.EntityProperties;
import model.entity.Hitbox;
import model.entity.J1;
import model.entity.J2;
import model.entity.Key;
import model.entity.Point;
import model.entity.Torch;
import model.map.Case;
import model.map.Map;


public class Room {

	private int upperLeftX, upperLeftY;
	private int width, height;

	private Case comp[][];
	private RoomType type;
	private LinkedList listeDoors;
	private boolean visited;
	private Door firstDoor;


	public Room (int w, int h, Case[][] composition, int typeID) {

		upperLeftX = -1; //On remplie à -1 parce que l'emplacement dès salle sera fait plus tard
		upperLeftY = -1;
		width = w;
		height = h;

		listeDoors = new LinkedList();
		visited = false;
		firstDoor = null;

		comp = new Case[w][h];

		type = null;
		RoomType[] roomTypes = RoomType.values();
		int k = 0;

		while (k < roomTypes.length && type == null) {
			if (roomTypes[k].getID() == typeID)
				type = roomTypes[k];
			k++;
		}

		for (int i = 0; i < w; i++) {

			for (int j = 0; j < h; j++) {
				comp[i][j] = composition[i][j];
			}
		}

	}

	public Room (int x, int y) {
		upperLeftX = x;
		upperLeftY = y;
		width = 0;
		height = 0;
	}

	public void spawnEntities (Map m, int nbMobsRandomlyPlaced) {

		Model model = Model.getInstance();

		//Spawn des entités placées dans le JSON
		for (int i = 0; i < width; i++) {

			for (int j = 0; j < height; j++) {
				Case c = comp[i][j];
				EntityProperties entityProperties = c.getType().getEntityProperties();

				if (entityProperties != null) {
					double entityWidth = entityProperties.getWidth();
					double entityHeight = entityProperties.getHeight();
					int x = i + upperLeftX;
					int y = j + upperLeftY;

					if (entityProperties == EntityProperties.J1) {

						try {
							J1.getInstance().m_hitbox = new Hitbox(x, y, entityWidth, entityHeight, J1.getInstance());
						}
						catch (Exception ex) {
							Entity e = model.createEntity(x, y, entityProperties);
							//à enelever plus tard
							model.createLightSource(e);
						}
					} else if (entityProperties == EntityProperties.J2) {

						try {
							J2.getInstance().m_hitbox = new Hitbox(x, y, entityWidth, entityHeight, J2.getInstance());
						}
						catch (Exception ex) {
							Entity e = model.createEntity(x, y, entityProperties);
							//à enelever plus tard
							model.createLightSource(e);
						}
					} else if (entityProperties == EntityProperties.KEY) {

						try {
							Key.getInstance().m_hitbox = new Hitbox(x, y, entityWidth, entityHeight, Key.getInstance());
						}
						catch (Exception ex) {
							Entity e = model.createEntity(x, y, entityProperties);
							//à enelever plus tard
							model.createLightSource(e);
						}
					} else if (entityProperties == EntityProperties.TORCH) {

						try {
							Torch.getInstance().m_hitbox = new Hitbox(x, y, entityWidth, entityHeight, Torch.getInstance());
						}
						catch (Exception ex) {
							Entity e = model.createEntity(x, y, entityProperties);
							model.createLightSource(e);
						}
					} else {
						Entity e = model.createEntity(x, y, entityProperties);
						//à enelever plus tard
						model.createLightSource(e);
					}
				}
			}
		}

		//Spawn random
		int iterationsSinceLastSuccess = 0;
		int placed = 0;
		int weightSum = 0;
		EntityProperties[] eps = EntityProperties.values();
		Random random = new Random();
		Entity j1 = J1.getInstance();
		Entity j2 = J2.getInstance();
		double minDistance = 1.5;

		for (int i = 0; i < eps.length; i++) {
			weightSum += eps[i].getSpawnWeight();
		}

		while (iterationsSinceLastSuccess < 50 && placed < nbMobsRandomlyPlaced) {
			EntityProperties ep = getWeightedRandom(weightSum);
			double x = random.nextDouble() * (width - 2) + upperLeftX + 1;
			double y = random.nextDouble() * (height - 2) + upperLeftY + 1;
			Entity e = model.createEntity(x, y, ep);

			Point p1 = e.getHibox().getP1();
			Point p2 = e.getHibox().getP2();
			Point p3 = e.getHibox().getP3();
			Point p4 = e.getHibox().getP4();

			if (e.getHibox().deplacementValide(p1, p2, p3, p4)) {

				if (e.distance(j1) > minDistance && e.distance(j2) > minDistance) {
					iterationsSinceLastSuccess = 0;
					placed++;
				} else {
					e.deleteEntity();
					iterationsSinceLastSuccess++;
				}
			} else {
				e.deleteEntity();
				iterationsSinceLastSuccess++;
			}

		}

	}

	private EntityProperties getWeightedRandom (int weightSum) {
		EntityProperties[] eps = EntityProperties.values();
		Random random = new Random();
		double r = random.nextDouble();
		double progress = 0;

		for (int i = 0; i < eps.length; i++) {
			progress += ((double) eps[i].getSpawnWeight()) / weightSum;

			if (r < progress) {
				return eps[i];
			}
		}
		throw new RuntimeException("Revoir code de cette fonction");

	}

	public int getUpperLeftX () {
		return upperLeftX;
	}

	public int getUpperLeftY () {
		return upperLeftY;
	}

	public int getWidth () {
		return width;
	}

	public int getHeight () {
		return height;
	}

	public RoomType getType () {
		return type;
	}

	public Case[][] getComp () {
		return comp;
	}

	public void setUpperLeft (int x, int y) {
		this.upperLeftX = x;
		this.upperLeftY = y;
	}

	public boolean containsPoint (int x, int y) {
		return (x >= upperLeftX && x <= upperLeftX + width - 1 && y >= upperLeftY && y <= upperLeftY + height - 1);
	}

	public boolean containsHitbox (Hitbox h) {
		Hitbox eR = new Hitbox(upperLeftX + 1, upperLeftY + 1, width - 2, height - 2, null);
		return h.colisionWithItbox(eR);
	}

	public boolean getVisited () {
		return visited;
	}

	public void setVisited (boolean v) {
		this.visited = v;
	}

	public Door getFirstDoor () {
		return this.firstDoor;
	}

	public void setFirstDoor (Door door) {
		this.firstDoor = door;
	}

	public void add (Door e) {
		listeDoors.insertAt(listeDoors.length(), e);
	}

	public ICollection getDoors () {
		return listeDoors;
	}

}
