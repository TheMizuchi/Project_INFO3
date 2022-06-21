package model.map.generator;

import java.util.Random;

import model.Model;
import model.entity.Entity;
import model.entity.EntityProperties;
import model.map.Case;
import model.map.Map;


public class Room {

	Model model;

	private int upperLeftX, upperLeftY;
	private int width, height;

	private Case comp[][];
	private RoomType type;


	public Room (Model m, int w, int h, Case[][] composition, int typeID) {
		model = m;

		upperLeftX = -1; //On remplie à -1 parce que l'emplacement dès salle sera fait plus tard
		upperLeftY = -1;
		width = w;
		height = h;

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

	public Room () {}

	public void spawnEntities (Map m, int nbMobsRandomlyPlaced) {

		//Spawn des entités placées dans le JSON
		for (int i = 0; i < width; i++) {

			for (int j = 0; j < height; j++) {
				Case c = comp[i][j];
				EntityProperties entityProperties = c.getType().getEntityProperties();

				if (entityProperties != null) {
					int x = i + upperLeftX;
					int y = j + upperLeftY;
					Entity e = model.createEntity(x, y, entityProperties);
					//à enelever plus tard 
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
		Entity j1 = model.getJ(1);
		Entity j2 = model.getJ(2);
		double minDistance = 1.5;

		for (int i = 0; i < eps.length; i++) {
			weightSum += eps[i].getSpawnWeight();
		}

		while (iterationsSinceLastSuccess < 50 && placed < nbMobsRandomlyPlaced) {
			EntityProperties ep = getWeightedRandom(weightSum);
			double x = random.nextDouble() * width + upperLeftX;
			double y = random.nextDouble() * height + upperLeftY;
			Entity e = Entity.createEntityWithoutView(x, y, ep);

			if (e.getHibox().deplacementValide(x, y)) {

				if (distance(e, j1) > minDistance && distance(e, j2) > minDistance) {
					model.createEntity(x, y, ep);
					iterationsSinceLastSuccess = 0;
					placed++;
				}
			} else {
				iterationsSinceLastSuccess++;
			}

		}

	}

	private double distance (Entity e1, Entity e2) {
		return Math.sqrt(Math.pow(e1.getPosX() - e2.getPosX(), 2) + Math.pow(e1.getPosY() - e2.getPosY(), 2));
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

}