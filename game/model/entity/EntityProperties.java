package model.entity;

import model.Model;


public enum EntityProperties {

	COWBOY(0, 1, EntityType.ALLY), J1(1, 1, EntityType.ALLY), J2(2, 1, EntityType.ALLY), TORCH(3, 1, EntityType.ITEM),
	SKELETON(4, 1, EntityType.ENEMY), BAT(5, 1, EntityType.NEUTRAL), DART_MONKEY(6, 1, EntityType.ENEMY),
	BLOON(7, 1, EntityType.ENEMY), ENTITY(8, 1, EntityType.NEUTRAL);


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
