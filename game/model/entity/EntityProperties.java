package model.entity;

public enum EntityProperties {

	COWBOY(0, 1, EntityType.ALLY, false), J1(1, 1, EntityType.ALLY, false), J2(2, 1, EntityType.ALLY, false),
	TORCH(3, 1, EntityType.ITEM, false), SKELETON(4, 1, EntityType.ENEMY, true), BAT(5, 1, EntityType.NEUTRAL, true),
	ARCHER(6, 1, EntityType.ENEMY, true), BLOON(7, 1, EntityType.ENEMY, true), DOGE(8, 1, EntityType.ENEMY, false),
	MYSTERY(9, 1, EntityType.ENEMY, false), ENTITY(10, 1, EntityType.NEUTRAL, false),
	BLOON_BOSS(11, 1, EntityType.ENEMY, false);


	private final int id;
	private final EntityType entityType;
	private final int initialPv;
	private final boolean possessable;


	EntityProperties (final int id, final int initialPv, final EntityType entityType, final boolean possessable) {
		this.id = id;
		this.entityType = entityType;
		this.initialPv = initialPv;
		this.possessable = possessable;
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

}
