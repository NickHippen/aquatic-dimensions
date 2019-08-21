package net.infernalrealms.aquatic_dimensions.config;

import java.io.File;
import java.io.IOException;

import com.electronwill.nightconfig.core.file.FileConfig;

import net.infernalrealms.aquatic_dimensions.AquaticDimensionsPlugin;

public enum TOMLConfig {
	
	MONSTERS("monsters/monsters"),
	;
	
	private static final String SUFFIX = ".toml";
	
	private final String fileName;
	
	private TOMLConfig(String name) {
		fileName = name + SUFFIX;
		generateConfigIfMissing();
	}
	
	private void generateConfigIfMissing() {
		File file = getFile();
		file.getParentFile().mkdirs();
		try {
			file.createNewFile();
		} catch (IOException e) {
			AquaticDimensionsPlugin.getPlugin().getLogger().warning("Failed to create config: " + getFileName());
			e.printStackTrace();
		}
	}
	
	public File getFile() {
		return new File(AquaticDimensionsPlugin.getPlugin().getDataFolder(), getFileName());
	}
	
	public FileConfig getConfig() {
		return FileConfig.of(getFile());
	}
	
	public String getFileName() {
		return fileName;
	}
	
}
