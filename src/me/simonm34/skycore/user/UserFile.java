package me.simonm34.skycore.user;

import me.simonm34.skycore.Core;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;

public class UserFile {
    private FileConfiguration userData;
    private File userFile;

    public FileConfiguration setupUser(UUID uuid) {
        userFile = new File(Core.getCore().getDataFolder() + File.separator + "userdata", uuid + ".yml");

        boolean defaults = false;
        if (!userFile.exists()) {
            defaults = true;
            try {
                userFile.createNewFile();
            } catch(IOException e) {
                Core.getCore().getLogger().log(Level.WARNING, "Could not create \"" + uuid + ".yml\" file!");
            }
        }
        userData = YamlConfiguration.loadConfiguration(userFile);

        if (defaults) {
            userData.set("ranks.rank", Core.getCore().getRankManager().getDefaultRank().getName());
            userData.set("ranks.subrank", "None");
            userData.set("data.nickname", "None");
            userData.set("data.balance", 500);
            userData.set("info.is flying", false);
            userData.set("info.is god", false);
            userData.set("homes", new ArrayList<>());
            userData.set("punishments", new ArrayList<>());
            saveUser();
        }
        return userData;
    }
    public void saveUser() {
        try {
            userData.save(userFile);
        } catch (IOException e) {
            Core.getCore().getLogger().log(Level.WARNING, "Could not save \"" + userFile.getName() + "\" file!");
        }
    }
    public void setupDir() {
        userFile = new File(Core.getCore().getDataFolder(), "userdata");
        if (!userFile.exists())
            userFile.mkdir();
    }
}
