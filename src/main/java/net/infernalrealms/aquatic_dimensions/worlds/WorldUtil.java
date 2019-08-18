package net.infernalrealms.aquatic_dimensions.worlds;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

public class WorldUtil {
	
	public static World loadInstancedWorld(File worldDir) {
		UUID uuid = UUID.randomUUID();
		return loadWorld(worldDir, new File(Bukkit.getWorldContainer(), "instances/" + worldDir.getName() + "-" + uuid));
	}
	
	public static World loadWorld(File worldDir, File worldDirDest) {
			try {
				FileUtils.copyDirectory(worldDir, worldDirDest);
				new File(worldDirDest, "uid.dat").delete();
			} catch (IOException e) {
				e.printStackTrace();
			}
			World world = WorldCreator.name("instances/" + worldDirDest.getName()).generator(new EmptyGenerator()).createWorld();
			return world;
	}
	
}
