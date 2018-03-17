package me.simonm34.skyblock.utils;

import me.simonm34.skycore.Core;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;

public class FileManager {
    private FileConfiguration islandData;
    private File islandFile;

    public FileConfiguration setupIsland(UUID islandID) {
        islandFile = new File(Core.getCore().getDataFolder() + File.separator + "islands", islandID + ".yml");
        if (!islandFile.exists()) {
            try {
                islandFile.createNewFile();
            } catch (IOException e) {
                Core.getCore().getLogger().log(Level.WARNING, "Could not create \"" + islandFile.getName() + "\" file!");
            }
        }
        islandData = YamlConfiguration.loadConfiguration(islandFile);
        return islandData;
    }
    public FileConfiguration setupUpgrades() {
        File file = new File(Core.getCore().getDataFolder(), "upgrades.yml");
        if (!file.exists())
            Core.getCore().saveResource("upgrades.yml", false);
        return YamlConfiguration.loadConfiguration(file);
    }
    public void saveIsland() {
        try {
            islandData.save(islandFile);
        } catch (IOException e) {
            Core.getCore().getLogger().log(Level.WARNING, "Could not create \"" + islandFile.getName() + "\" file!");
        }
    }

    public void setupDirectory() {
        islandFile = new File(Core.getCore().getDataFolder() + File.separator + "islands");
        if (!islandFile.exists())
            islandFile.mkdir();
    }
}
