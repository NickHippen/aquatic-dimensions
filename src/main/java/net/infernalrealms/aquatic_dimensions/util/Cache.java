package net.infernalrealms.aquatic_dimensions.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class Cache<K,V> {

	private Map<K,V> cacheMap;
	
	protected Cache() {
		cacheMap = new HashMap<>();
	}

	/**
	 * Gets an entry from the cache. Null if not present.
	 * @param key
	 * @return the entry for the key, or null if not present
	 */
	public V getEntry(K key) {
		return cacheMap.getOrDefault(key, null);
	}
	
	public void addEntry(K key, V value) {
		cacheMap.put(key, value);
	}
	
	public V removeEntry(K key) {
		return cacheMap.remove(key);
	}
	
	public Set<Map.Entry<K,V>> getEntries() {
		return cacheMap.entrySet();
	}
	
	public abstract void loadCache();
	
}
