package model.entity;

public enum EntityProperties {

	COWBOY(0, 1, 1, EntityType.ALLY, false, 0, 0.5, 0.5), J1(1, 20, 1, EntityType.ALLY, false, 0, 0.5, 0.5),
	J2(2, 20, 1, EntityType.ALLY, false, 0, 0.5, 0.5), TORCH(3, 1, 0, EntityType.ITEM, false, 0, 0.5, 0.5),
	SKELETON(4, 1, 1, EntityType.ENEMY, true, 7, 0.5, 0.5), BAT(5, 1, 1, EntityType.NEUTRAL, true, 1, 0.5, 0.5),
	ARCHER(6, 1, 1, EntityType.ENEMY, true, 1, 0.5, 0.5), BLOON(7, 1, 1, EntityType.ENEMY, true, 0, 0.5, 0.5),
	DOGE(8, 1, 1, EntityType.ENEMY, false, 0, 0.5, 0.5), MYSTERY(9, 1, 1, EntityType.ENEMY, false, 0, 0.5, 0.5),
	BLOON_BOSS(10, 1, 1, EntityType.ENEMY, false, 0, 0.5, 0.5), KEY(11, 1, 0, EntityType.ITEM, false, 0, 0.5, 0.5),
	DOOR(12, 1, 0, EntityType.ITEM, false, 0, 1, 1), STAIRS(12, 1, 0, EntityType.ITEM, false, 0, 1, 1),
	BATSPAWNER(13, 1, 0, EntityType.NEUTRAL, false, 0, 0.5, 0.5);


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
