package me.simonm34.skycore.utils;

import me.simonm34.skycore.Core;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class FileManager {
    private FileConfiguration ranksData;
    private FileConfiguration warpsData;
    private FileConfiguration nameUUIDsData;
    private FileConfiguration shopsData;
    private FileConfiguration kitsData;
    private File ranksFile;
    private File warpsFile;
    private File nameUUIDsFile;
    private File shopsFile;
    private File kitsFile;

    public FileConfiguration setupRanks() {
        ranksFile = new File(Core.getCore().getDataFolder(), "ranks.yml");
        if (!ranksFile.exists())
            Core.getCore().saveResource("ranks.yml", false);
        return ranksData = YamlConfiguration.loadConfiguration(ranksFile);
    }
    public FileConfiguration setupWarps() {
        warpsFile = new File(Core.getCore().getDataFolder(), "warps.yml");
        if (!warpsFile.exists())
            Core.getCore().saveResource("warps.yml", false);
        return warpsData = YamlConfiguration.loadConfiguration(warpsFile);
    }
    public FileConfiguration setupNameUUIDs() {
        nameUUIDsFile = new File(Core.getCore().getDataFolder(), "namestorage.yml");
        if (!nameUUIDsFile.exists())
            Core.getCore().saveResource("ranks.yml", false);
        return nameUUIDsData = YamlConfiguration.loadConfiguration(nameUUIDsFile);
    }
    public FileConfiguration setupShops() {
        shopsFile = new File(Core.getCore().getDataFolder(), "shops.yml");
        if (!shopsFile.exists())
            Core.getCore().saveResource("shops.yml", false);
        return shopsData = YamlConfiguration.loadConfiguration(shopsFile);
    }
    public FileConfiguration setupKits() {
        kitsFile = new File(Core.getCore().getDataFolder(), "kits.yml");
        if (!kitsFile.exists())
            Core.getCore().saveResource("kits.yml", false);
        return kitsData = YamlConfiguration.loadConfiguration(kitsFile);
    }

    public FileConfiguration getNameUUIDs() {
        return nameUUIDsData;
    }
    public FileConfiguration getShops() {
        return shopsData;
    }

    public void saveWarps() {
        try {
            warpsData.save(warpsFile);
        } catch (IOException e) {
            Core.getCore().getLogger().log(Level.WARNING, "Could not save \"warps.yml\" file!");
        }
    }
    public void saveNameUUIDs() {
        try {
            warpsData.save(warpsFile);
        } catch (IOException e) {
            Core.getCore().getLogger().log(Level.WARNING, "Could not save \"namestorage.yml\" file!");
        }
    }
}
