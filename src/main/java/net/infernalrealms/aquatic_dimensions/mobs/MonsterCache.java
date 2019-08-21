package net.infernalrealms.aquatic_dimensions.mobs;

import net.infernalrealms.aquatic_dimensions.util.Cache;

public class MonsterCache extends Cache<String, MonsterData> {

	private static MonsterCache SINGLETON;
	
	private static final MonsterCacheLoader monsterCacheLoader = new TOMLMonsterCacheLoader();
	
	private MonsterCache() {
		loadCache();
	}
	
	@Override
	public void loadCache() {
		monsterCacheLoader.loadCache(this);
	}
	
	public static MonsterCache getInstance() {
		if (SINGLETON == null) {
			SINGLETON = new MonsterCache();
		}
		return SINGLETON;
	}

}
