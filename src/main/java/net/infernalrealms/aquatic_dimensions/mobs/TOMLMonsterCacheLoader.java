package net.infernalrealms.aquatic_dimensions.mobs;

import java.util.Map;

import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.conversion.ObjectConverter;
import com.electronwill.nightconfig.core.file.FileConfig;

import net.infernalrealms.aquatic_dimensions.config.TOMLConfig;

public class TOMLMonsterCacheLoader implements MonsterCacheLoader {

	public void loadCache(MonsterCache cache) {
		try (FileConfig config = TOMLConfig.MONSTERS.getConfig()) {
			config.load();
			ObjectConverter converter = new ObjectConverter();
			Map<String, Object> monsterMap = config.valueMap();
			for (String key : monsterMap.keySet()) {
				Config monsterDataConfig = (Config) monsterMap.get(key);
				MonsterData monsterData = converter.toObject(monsterDataConfig, MonsterData::new);
				cache.addEntry(key, monsterData);
			}
		}
	}
	
}
