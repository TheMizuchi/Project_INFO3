package model.map;

import model.entity.EntityProperties;


public enum TileType {

	WALL(0, 0, null), FLOOR(1, 1, null), COWBOY(EntityProperties.COWBOY.spawnerID(), 1, EntityProperties.COWBOY),
	J1(EntityProperties.J1.spawnerID(), 1, EntityProperties.J1),
	J2(EntityProperties.J2.spawnerID(), 1, EntityProperties.J2),
	TORCH(EntityProperties.TORCH.spawnerID(), 1, EntityProperties.TORCH),
	SKELETON(EntityProperties.SKELETON.spawnerID(), 1, EntityProperties.SKELETON),
	BAT(EntityProperties.BAT.spawnerID(), 1, EntityProperties.BAT),
	DART_MONKEY(EntityProperties.DART_MONKEY.spawnerID(), 1, EntityProperties.DART_MONKEY),
	BLOON(EntityProperties.BLOON.spawnerID(), 1, EntityProperties.BLOON);


	private int id;
	private int textureID;
	private EntityProperties entityProperties;


	TileType (final int id, final int tid, final EntityProperties entityProperties) {
		this.id = id;
		this.textureID = tid;
		this.entityProperties = entityProperties;
	}

	public int getID () {
		return id;
	}

	public int getTextureID () {
		return textureID;
	}

	public EntityProperties getEntityProperties () {
		return entityProperties;
	}

}
