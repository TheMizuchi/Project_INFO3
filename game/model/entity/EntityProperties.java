package model.entity;

public enum EntityProperties {

	COWBOY(0, 1, 1, EntityType.ALLY, false, 0, 0.5, 0.5), J1(1, 20, 1, EntityType.ALLY, false, 0, 0.5, 0.7),
	J2(2, 20, 1, EntityType.ALLY, false, 0, 0.5, 0.7), TORCH(3, 1, 0, EntityType.ITEM, false, 0, 0.3, 0.3),
	SKELETON(4, 2, 1, EntityType.ENEMY, true, 5, 0.5, 0.7), BAT(5, 1, 1, EntityType.NEUTRAL, true, 2, 0.5, 0.5),
	ARCHER(6, 2, 1, EntityType.ENEMY, true, 4, 0.5, 0.9), BLOON(7, 1, 3, EntityType.ENEMY, true, 3, 0.5, 0.6),
	DOGE(8, 10, 1, EntityType.ENEMY, false, 0, 1.1, 1.3), MYSTERY(9, 10, 1, EntityType.ENEMY, false, 0, 3.3, 2.2),
	BLOON_BOSS(10, 1, 1, EntityType.ENEMY, false, 0, 0.5, 0.5), KEY(11, 1, 0, EntityType.ITEM, false, 0, 0.5, 0.5),
	DOOR(12, 1, 0, EntityType.OBSTACLE, false, 0, 1, 1), STAIRS(13, 1, 0, EntityType.ITEM, false, 0, 1, 1),
	BATSPAWNER(14, 1, 0, EntityType.NEUTRAL, false, 0, 0.4, 0.3), ARROW(15, 1, 0, EntityType.ENEMY, false, 0, 0.2, 0.2);


	public static int ENTITY_NUMBER = EntityProperties.values().length;

	private final int id;
	private final int initialPv;
	private final int damages;
	private final boolean possessable;
	private final EntityType entityType;
	private final int spawnWeight;
	private final double width, height;


	EntityProperties (final int id, final int initialPv, final int damages, final EntityType entityType,
			final boolean possessable, final int spawnWeight, final double width, final double height) {
		this.id = id;
		this.entityType = entityType;
		this.initialPv = initialPv;
		this.possessable = possessable;
		this.spawnWeight = spawnWeight;
		this.damages = damages;
		this.width = width;
		this.height = height;
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

}
