package model.entity;

public enum EntityProperties {

	COWBOY(0, 1, 1, EntityType.ALLY, false, 0, 0.5, 0.5, 0.0, 0.0),
	J1(1, 20, 1, EntityType.ALLY, false, 0, 0.5, 0.7, 0.0, 0.1),
	J2(2, 20, 1, EntityType.ALLY, false, 0, 0.5, 0.7, 0.0, 0.2),
	TORCH(3, 1, 0, EntityType.ITEM, false, 0, 0.3, 0.3, 0.0, 0.1),
	SKELETON(4, 2, 1, EntityType.ENEMY, true, 5, 0.5, 0.7, 0.2, 0.0),
	BAT(5, 1, 1, EntityType.NEUTRAL, true, 2, 0.5, 0.5, 0.0, 0.3),
	ARCHER(6, 2, 1, EntityType.ENEMY, true, 4, 0.6, 0.9, 0.2, 0.15),
	BLOON(7, 1, 3, EntityType.ENEMY, true, 3, 0.5, 0.7, 0, 0.2),
	DOGE(8, 10, 1, EntityType.ENEMY, false, 0, 1.2, 1.8, 0.0, 0.5),
	MYSTERY(9, 10, 1, EntityType.ENEMY, false, 0, 3.5, 2.2, 0.0, 0.7),
	BLOON_BOSS(10, 1, 1, EntityType.ENEMY, false, 0, 0.5, 0.7, 0.0, 0.2),
	KEY(11, 1, 0, EntityType.ITEM, false, 0, 0.5, 0.5, 0.0, 0.0),
	DOOR(12, 1, 0, EntityType.OBSTACLE, false, 0, 1, 1, 0.0, 0.2),
	STAIRS(13, 1, 0, EntityType.ITEM, false, 0, 1, 1, 0.0, 0.2),
	BATSPAWNER(14, 1, 0, EntityType.NEUTRAL, false, 0, 0.4, 0.3, 0.0, 0.1),
	ARROW(15, 1, 0, EntityType.ENEMY, false, 0, 0.5, 0.2, 0.0, 0.2);


	public static int ENTITY_NUMBER = EntityProperties.values().length;

	private final int id;
	private final int initialPv;
	private final int damages;
	private final boolean possessable;
	private final EntityType entityType;
	private final int spawnWeight;
	private final double width, height, dxView, dyView;


	EntityProperties (final int id, final int initialPv, final int damages, final EntityType entityType,
			final boolean possessable, final int spawnWeight, final double width, final double height,
			final double deltaX, final double deltaY) {
		this.id = id;
		this.entityType = entityType;
		this.initialPv = initialPv;
		this.possessable = possessable;
		this.spawnWeight = spawnWeight;
		this.damages = damages;
		this.width = width;
		this.height = height;
		this.dxView = deltaX;
		this.dyView = deltaY;
	}

	public int getID () {
		return id;
	}

	public int getInitialPv () {
		return initialPv;
	}

	public int spawnerID () {
		return id + 100;
	}

	public EntityType getEntityType () {
		return entityType;
	}

	public boolean getPossessable () {
		return possessable;
	}

	public int getSpawnWeight () {
		return spawnWeight;
	}

	public int getDamages () {
		return damages;
	}

	public double getWidth () {
		return width;
	}

	public double getHeight () {
		return height;
	}

	public double getDxView () {
		return dxView;
	}

	public double getDyView () {
		return dyView;
	}

}
