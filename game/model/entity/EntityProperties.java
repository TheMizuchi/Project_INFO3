package model.entity;

public enum EntityProperties {

	COWBOY(0, 1, EntityType.ALLY, 0), J1(1, 1, EntityType.ALLY, 0), J2(2, 1, EntityType.ALLY, 0),
	TORCH(3, 1, EntityType.ITEM, 0), SKELETON(4, 1, EntityType.ENEMY, 7), BAT(5, 1, EntityType.NEUTRAL, 1),
	DART_MONKEY(6, 1, EntityType.ENEMY, 0), BLOON(7, 1, EntityType.ENEMY, 0), DOGE(8, 1, EntityType.ENEMY, 0),
	MYSTERY(9, 1, EntityType.ENEMY, 0), KEY(10, 1, EntityType.ITEM, 0), ENTITY(11, 1, EntityType.NEUTRAL, 0);


	private final int id;
	private final int initialPv;
	private final EntityType entityType;
	private final int spawnWeight;


	EntityProperties (final int id, final int initialPv, final EntityType entityType, int spawnWeight) {
		this.id = id;
		this.entityType = entityType;
		this.initialPv = initialPv;
		this.spawnWeight = spawnWeight;
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

	public int getSpawnWeight () {
		return spawnWeight;
	}

}