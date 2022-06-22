package model.entity;

public enum EntityProperties {

	COWBOY(0, 1, EntityType.ALLY), J1(1, 1, EntityType.ALLY), J2(2, 1, EntityType.ALLY), TORCH(3, 1, EntityType.ITEM),
	SKELETON(4, 1, EntityType.ENEMY), BAT(5, 1, EntityType.NEUTRAL), ARCHER(6, 1, EntityType.ENEMY),
	BLOON(7, 1, EntityType.ENEMY), DOGE(8, 1, EntityType.ENEMY), MYSTERY(9, 1, EntityType.ENEMY),
	ENTITY(10, 1, EntityType.NEUTRAL), BLOON_BOSS(11, 1, EntityType.ENEMY);


	private final int id;
	private final EntityType entityType;
	private final int initialPv;


	EntityProperties (final int id, final int initialPv, final EntityType entityType) {
		this.id = id;
		this.entityType = entityType;
		this.initialPv = initialPv;
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

}
