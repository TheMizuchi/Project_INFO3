package model.map.generator;

import java.util.Random;

import edu.polytech.oop.collections.LinkedList;
import model.Model;
import model.entity.Entity;
import model.entity.EntityProperties;
import model.entity.J1;
import model.entity.J2;
import model.entity.Point;
import model.map.Case;
import model.map.Map;


public class Room {

	private int upperLeftX, upperLeftY;
	private int width, height;

	private Case comp[][];
	private RoomType type;
	private LinkedList listeEntity; //Portes non incluses


	public Room (int w, int h, Case[][] composition, int typeID) {

		upperLeftX = -1; //On remplie à -1 parce que l'emplacement dès salle sera fait plus tard
		upperLeftY = -1;
		width = w;
		height = h;

		listeEntity = new LinkedList();

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
					int x = i + upperLeftX;
					int y = j + upperLeftY;
					Entity e = model.createEntity(x, y, entityProperties);
					listeEntity.insertAt(listeEntity.length(), e);
					//à décommenter plus tard
					//if (e.getID() == EntityProperties.TORCH.getID())
					model.createLightSource(e);
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
					listeEntity.insertAt(listeEntity.length(), e);
					iterationsSinceLastSuccess = 0;
					placed++;
				}
			} else {
				model.deleteEntity(e);
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

	public LinkedList getListeEntity () {
		return listeEntity;
	}

}
